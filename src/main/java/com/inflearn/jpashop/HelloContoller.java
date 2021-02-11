package com.inflearn.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloContoller {
    @GetMapping(value = "hello")
    public String hello(Model model){

        model.addAttribute("test","hello");
        return "hello";
    }
}
