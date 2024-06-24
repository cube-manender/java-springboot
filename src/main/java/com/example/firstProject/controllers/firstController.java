package com.example.firstProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class firstController {
    @RequestMapping("/test")
    @ResponseBody
    public String firstHandler() {
        System.out.println("MANENDER");
        return "Manender";
    }
}
