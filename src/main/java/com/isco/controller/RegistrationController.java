package com.isco.controller;

import com.isco.model.User;
import com.isco.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/registration")
@Validated
public class RegistrationController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String index(){
        return "registration";
    }

    @PostMapping
    public String registration(@Valid @RequestParam Map<String, String> userMap) throws IllegalAccessException {
        userService.getUserFromMapAndSave(userMap);
        return "index";
    }
}
