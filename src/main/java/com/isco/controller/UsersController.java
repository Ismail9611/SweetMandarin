package com.isco.controller;

import com.isco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping
    public String index(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size, Model model) {
        model.addAttribute("users", userService.getAllUsers(page, size));
        return "/users";
    }


}
