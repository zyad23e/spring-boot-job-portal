package com.zyad.platform.jobportal.controller;

import com.zyad.platform.jobportal.entity.Users;
import com.zyad.platform.jobportal.entity.UsersType;
import com.zyad.platform.jobportal.services.UsersService;
import com.zyad.platform.jobportal.services.UsersTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
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

    // @Valid means before this method runs, check that this object's field satisfies the validation rules.
    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users){
        //System.out.println("User:: " + users);
        usersService.addNew(users);
        return "dashboard";
    }
}
