package com.xuuuuu.sringboot_jsp_demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class index {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("home")
    public String home(){
        return "home";
    }

}
