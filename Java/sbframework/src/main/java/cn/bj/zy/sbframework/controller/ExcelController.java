package cn.bj.zy.sbframework.controller;

import cn.bj.zy.core.BaseEntity;
import cn.bj.zy.sbframework.mapper.platform.TB_CHANYEMapper;
import cn.bj.zy.sbframework.model.platform.JBBS_USER;
import cn.bj.zy.sbframework.model.platform.TB_CHANYE;
import cn.bj.zy.sbframework.model.platform.TbTeacher;
import cn.bj.zy.sbframework.model.primary.TB_USER;
import cn.bj.zy.sbframework.service.platform.JbbsUserService;
import cn.bj.zy.sbframework.service.platform.TeacherService;
import cn.bj.zy.sbframework.service.primary.UserService;
import cn.bj.zy.util.ExcelTools;
import cn.bj.zy.util.ExcelUtil;
import cn.bj.zy.util.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 　　┃　 　 ┗━━━━┓ + +
 * 　 ┃ 　　　　　　   ┣┓
 * 　┃ 　　　　　　   ┏┛
 * ┗ ┓┓┏━┳┓┏ ┛ + + + +
 * 　 ┃┫┫　┃┫┫
 * 　┗┻┛　┗┻┛+ + + +
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @author Huang Yukun
 * @
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("api/excel")
public class ExcelController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    TB_CHANYEMapper tb_chanyeMapper;

    @Resource
    UserService userService;



    /**
     * 导入excel表
     *
     * @version 1.0
     * @since 1.0
     */
    @RequestMapping(path = "/importEmployee", method = RequestMethod.POST)
    public Map<String, Object> uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            List<TB_CHANYE> employeeDTOList = ExcelUtil.importExcel(TB_CHANYE.class, file.getInputStream());
            //可做持久化操作，现只打印观察
            for (TB_CHANYE employeeDTO : employeeDTOList) {
                //logger.info("name=" + employeeDTO.getName() + ",telephone=" + employeeDTO.getTelephone() + ",sex=" + employeeDTO.getSex());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @RequestMapping("/chanye")
    @ResponseBody
    Map<String, Object> exportChanyeExcel(HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        try {
            response.setContentType("application/xls");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(("eeelist").getBytes("UTF-8"), "iso-8859-1") + ".xls");
            Map<Integer, String[]> paramMap = new HashMap<>();
            //excel第三行为下拉选择框
            paramMap.put(2, new String[]{"man", "women"});
            BufferedInputStream input = new BufferedInputStream(ExcelUtil.excelModelbyClass(TB_CHANYE.class, paramMap, null));
            byte buffBytes[] = new byte[1024];
            OutputStream os = response.getOutputStream();
            int read = 0;
            while ((read = input.read(buffBytes)) != -1) {
                os.write(buffBytes, 0, read);
            }
            os.flush();
            os.close();
            input.close();
            result.put("success", true);
        } catch (Exception e) {
            logger.error("downloadEmployeeModel() catch Exception ", e);
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
    public void exportExcel( HttpServletResponse response) throws Exception
    {
        List<TB_USER> list = userService.selectAllUsers();
        XSSFWorkbook wb = ExcelTools.createExcel(list, TB_USER.class, null);
        String filename = getExcelName("表格");
        if(null != filename)
            ExcelTools.writeExcel(response, filename, wb);
    }
    // 转化为excel名称
    private String getExcelName(String filename) {
        String[] allFilename = {filename, ".xlsx"};
        String excelName = StringUtil.join(allFilename);
        try {
            return URLEncoder.encode(excelName, "UTF-8");
        }catch(Exception e){
            return null;
        }
    }
}