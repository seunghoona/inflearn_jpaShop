package com.inflearn.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class HelloContoller {

    @GetMapping(value = "/")
    public String home(Model model){
        log.debug("로그 시작합니다.");
        return "home";
    }

}
