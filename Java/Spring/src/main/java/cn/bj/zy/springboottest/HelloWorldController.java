package cn.bj.zy.springboottest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("api")
@Controller
public class HelloWorldController {
    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "Hello World!";
    }

    @RequestMapping("excel/index.html")
    public String toIndex(){
        return "index";
    }
}
