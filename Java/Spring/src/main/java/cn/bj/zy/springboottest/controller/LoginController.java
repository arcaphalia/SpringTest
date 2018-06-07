package cn.bj.zy.springboottest.controller;

import cn.bj.zy.springboottest.model.TB_USER;
import cn.bj.zy.springboottest.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

public class LoginController {

    @Resource
    UserService userService;

    @RequestMapping("/admin/login.do")
    public String adminLoginDo(String username, String password, Map<String,Object> map){
        TB_USER tb_user= userService.findUserByUsername(username);
        if(null == tb_user){
            map.put("loginResult","用户名不存在");
            return  "admin/login";
        }else if(!password.equals(tb_user.getPassword())){
            map.put("loginResult","密码错误");
            return  "admin/login";
        }else{
            // 登录成功！
            return "admin/index";
        }
    }
}
