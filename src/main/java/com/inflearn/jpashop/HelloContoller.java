package com.inflearn.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloContoller {

    @GetMapping(value = "hello")
    public String hello(Model model){

        model.addAttribute("data","hello");
        return "hello";
    }

}
