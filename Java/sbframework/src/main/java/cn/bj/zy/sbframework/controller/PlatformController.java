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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
@RequestMapping("platform")
public class PlatformController {

    @Resource
    UserService userService;

    @Resource
    JbbsUserService jbbsUserService;

    @Resource
    TeacherService teacherService;

    @Resource
    TB_CHANYEMapper tb_chanyeMapper;

    @RequestMapping("/selectByFilter")
    @ResponseBody
    Map<String, Object> selectByFilter() {

        Map<String, Object> result = new HashMap<>();
        try {
            Map<String, Object> filter = new HashMap<>();
            List<TB_CHANYE> chanyes = tb_chanyeMapper.selectAll();
            result.put("success", true);
            result.put("rows", chanyes);
        } catch (Exception e) {
            result.put("success", false);
            result.put("errorMsg", e.getMessage());
        }
        return result;
    }

    @RequestMapping("/selectByPage")
    @ResponseBody
    Map<String, Object> selectByPage() throws Exception {

        Map<String, Object> result = new HashMap<>();
        try {
            PageInfo<TbTeacher> pageInfo = PageHelper.startPage(1, 10).doSelectPageInfo(() -> {
                        try {
                            teacherService.selectAll();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );

            result.put("success", true);
            result.put("total", pageInfo.getPages());
            result.put("rows", pageInfo.getList());
            result.put("page", pageInfo.getPageNum());
            result.put("records", pageInfo.getTotal());
            //result.put("rows", jbbsUserService.selectAllUsers());
        } catch (Exception e) {
            result.put("success", false);
            result.put("errorMsg", e.getMessage());
        }
        return result;
    }

    @RequestMapping("/selectByPage2")
    @ResponseBody
    Map<String, Object> selectByPage2() {

        Map<String, Object> result = new HashMap<>();
        try {
            PageHelper.startPage(1, 10);
            List<TbTeacher> list = teacherService.selectAll();
            PageInfo<TbTeacher> pageInfo = new PageInfo<>(list);

            result.put("success", true);
            result.put("total", pageInfo.getPages());
            result.put("rows", pageInfo.getList());
            result.put("page", pageInfo.getPageNum());
            result.put("records", pageInfo.getTotal());
            //result.put("rows", jbbsUserService.selectAllUsers());
        } catch (Exception e) {
            result.put("success", false);
            result.put("errorMsg", e.getMessage());
        }
        return result;
    }


    @RequestMapping("/insertPrimary")
    @ResponseBody
    Map<String, Object> insertPrimary() {
        Map<String, Object> result = new HashMap<>();
        try {
            TB_USER tb_user = new TB_USER();
            tb_user.setId(BaseEntity.createUUID());
            tb_user.setUserId("test");
            tb_user.setUsername("测试");
            tb_user.setPassword("8897877");
            tb_user.setIsRoot("1");
            tb_user.setRegisterDate(new Date());
            tb_user.setStatus(1);
            tb_user.setCreateTime(new Date());
            tb_user.setCreateUser("admin");
            userService.insert(tb_user);
            result.put("success", true);
            return result;
        } catch (Exception e) {
            result.put("success", false);
            result.put("errorMsg", e.getMessage());
            return result;
        }
    }

    @RequestMapping("/insertPlatform")
    @ResponseBody
    Map<String, Object> insertPlatform() {
        Map<String, Object> result = new HashMap<>();
        try {
            JBBS_USER jbbs_user = new JBBS_USER();
            jbbs_user.setId(BaseEntity.createUUID());
            jbbs_user.setLoginname("platform");
            jbbs_user.setName("测试platform");
            jbbs_user.setPwd("5566877");
            jbbs_user.setLastlogintime(new Date());
            jbbs_user.setCreateTime(new Date());
            jbbs_user.setCreateUser("admin");
            jbbsUserService.insert(jbbs_user);
            result.put("success", true);
            return result;
        } catch (Exception e) {
            result.put("success", false);
            result.put("errorMsg", e.getMessage());
            return result;
        }
    }

//        @RequestMapping("/update")
//        @ResponseBody
//        Map<String, Object> platform(){
//            Map<String, Object> result = new HashMap<>();
//            try {
//                List<JBBS_USER> users = jbbsUserService.selectAllUsers();
//                result.put("success", true);
//                return result;
//            } catch (Exception e) {
//                result.put("success", false);
//                result.put("errorMsg", e.getMessage());
//                return result;
//            }
//        }
}
