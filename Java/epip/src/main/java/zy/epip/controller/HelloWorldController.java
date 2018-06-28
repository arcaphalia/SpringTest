package zy.epip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {
    @RequestMapping("/excel/export")
    @ResponseBody
    public String index(HttpServletRequest request){
        ServletContext context = request.getSession().getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context);
        String[] result = wac.getBeanDefinitionNames();
        for(int i = 0;i<result.length;i++){
            System.err.println(result[i]);
        }
        return "Hello World!";
    }

    @RequestMapping("/toIndex.html")
    public String toIndex(){
        return "index";
    }
}
