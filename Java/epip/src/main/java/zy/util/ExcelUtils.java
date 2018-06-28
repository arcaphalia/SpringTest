package zy.util;

/**
 * 　　　　　　　　 ┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻━┓ + +
 * 　　　　　　 ┃　　　　　　　  ┃
 * 　　　　　　┃　　　━　　　  ┃ ++ + + +
 * 　　　　　████━████ ┃+
 * 　　　　　┃　　　　　　　  ┃ +
 * 　　　　 ┃　　　┻　　　  ┃
 * 　　　　┃　　　　　　　  ┃ + +
 * 　　　 ┗━┓　　　┏━━┛
 * 　　　　  ┃　　　┃
 * 　　　　 ┃　　　┃ + + + +
 * 　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　 ┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　┃　　　┃
 * 　   ┃　　　┃　　+
 * 　　┃　 　 ┗━━━━ ┓ + +
 * 　 ┃ 　　　　　　　  ┣┓
 * 　┃ 　　　　　　　┏┛
 * ┗ ┓┓┏━┳┓┏┛ + + + +
 * 　 ┃┫┫　┃┫┫
 * 　┗┻┛　┗┻┛+ + + +
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @author Huang Yukun
 * @
 */

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.*;
import zy.annotation.ExcelField;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * XSSF: 基于内存 SXSS: 基于内存和硬盘 HSSF:高水平的写入方式，对于单元格的数据类型要求完全匹配
 * @Description: Excel导出封装类
 * @author Huang Yukun
 */
public class ExcelUtils {


    // 生成excel，list导出的数据，list里的实体class，sumData合计数据
    public static <Q> XSSFWorkbook createExcel(List<Q> list, Class<Q> cls, Q sumData) throws IOException, IllegalArgumentException, IllegalAccessException {
        XSSFWorkbook wb = new XSSFWorkbook();  Field[] fields = cls.getDeclaredFields();
        ArrayList<String> headList = new ArrayList<String>();


        // 添加合计数据
        if (sumData != null) {
            list.add(sumData);
        }


        for (Field f : fields) {
            ExcelField field = f.getAnnotation(ExcelField.class);
            if (field != null) {
                headList.add(field.title());
            }
        }


        XSSFCellStyle style = getCellStyle(wb);
        XSSFSheet sheet = wb.createSheet();
        // 设置Excel表的第一行即表头
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headList.size(); i++) {
            XSSFCell headCell = row.createCell(i);
            headCell.setCellType(Cell.CELL_TYPE_STRING);
            headCell.setCellStyle(style);// 设置表头样式
            headCell.setCellValue(String.valueOf(headList.get(i)));
            // sheet.autoSizeColumn((short) i);// 设置单元格自适应
            sheet.setColumnWidth(0, 15 * 256);
        }


        for (int i = 0; i < list.size(); i++) {
            XSSFRow rowdata = sheet.createRow(i + 1);// 创建数据行
            Q q = list.get(i);
            Field[] ff = q.getClass().getDeclaredFields();
            int j = 0;
            for (Field f : ff) {
                ExcelField field = f.getAnnotation(ExcelField.class);
                if (field == null) {
                    continue;
                }
                f.setAccessible(true);
                Object obj = f.get(q);
                XSSFCell cell = rowdata.createCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                // 当数字时
                if (obj instanceof Integer) {
                    cell.setCellValue((Integer)obj);
                    // 将序号替换为123456
                    if (j == 0)
                        cell.setCellValue(i + 1);
                }
                // 当为字符串时
                else if (obj instanceof String)
                    cell.setCellValue((String)obj);
                    // 当为布尔时
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                    // 当为时间时
                else if (obj instanceof Date)
                    cell.setCellValue(DateUtil.getString((Date)obj));
                    // 当为时间时
                else if (obj instanceof Calendar)
                    cell.setCellValue((Calendar)obj);
                    // 当为小数时
                else if (obj instanceof Double)
                    cell.setCellValue((Double)obj);
                j++;
            }
        }


        if (sumData != null) {
            int rowIndex = list.size();
            XSSFRow sumRow = sheet.getRow(rowIndex);
            XSSFCell sumCell = sumRow.getCell(0);
            sumCell.setCellStyle(style);
            sumCell.setCellValue("合计");
        }
        return wb;
    }


    // 导出
    public static void writeExcel(HttpServletResponse response, String fileName, XSSFWorkbook wb) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        OutputStream ouputStream = null;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
        } finally {
            ouputStream.close();
        }
    }


    // 表头样式
    public static XSSFCellStyle getCellStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)12);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        style.setFillForegroundColor(HSSFColor.LIME.index);// 设置背景色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.SOLID_FOREGROUND);// 让单元格居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        style.setWrapText(true);// 设置自动换行
        style.setFont(font);
        return style;
    }


}
