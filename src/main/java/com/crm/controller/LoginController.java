package com.crm.controller;


import com.crm.entity.User;
import com.crm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String checkUser () {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegisterForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String postRegister(@ModelAttribute("user") @Valid User user){
        try {
            userService.registerNewUserAccount(user);
        } catch (Exception userExists) {
            return "error_registration";
        }
        return "login";
    }
}
