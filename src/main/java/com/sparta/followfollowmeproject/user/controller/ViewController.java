package com.sparta.followfollowmeproject.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/api/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/api/user/signup")
    public String showSignupPage() {
        return "signup";
    }


}