package com.zyad.platform.jobportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// For Spring MVC
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
