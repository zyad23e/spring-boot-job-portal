package com.zyad.platform.jobportal.controller;

import com.zyad.platform.jobportal.entity.Users;
import com.zyad.platform.jobportal.entity.UsersType;
import com.zyad.platform.jobportal.services.UsersTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService) {
        this.usersTypeService = usersTypeService;
    }

    @GetMapping("/register")
    public String register(Model model){
        List<UsersType> userTypes = usersTypeService.getAll();

        // the HTML view can now access the list of userTypes using the name 'getAllTypes'
        model.addAttribute("getAllTypes", userTypes);

        // give the HTML page a blank Users object to bind form fields to using the name 'user'
        model.addAttribute("user", new Users());
        return "register";

    }
}
