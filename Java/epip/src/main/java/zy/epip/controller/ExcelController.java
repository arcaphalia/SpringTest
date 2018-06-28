package zy.epip.controller;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import zy.epip.mapper.platform.TB_CHANYEMapper;
import zy.epip.model.platform.TB_CHANYE;
import zy.epip.model.primary.TB_USER;
import zy.epip.service.primary.UserService;
import zy.util.ExcelUtils;
import zy.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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

    @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
    public void exportExcel( HttpServletResponse response) throws Exception
    {
        List<TB_USER> list = userService.selectAllUsers();
        XSSFWorkbook wb = ExcelUtils.createExcel(list, TB_USER.class, null);
        String filename = getExcelName("表格");
        if(null != filename)
            ExcelUtils.writeExcel(response, filename, wb);
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