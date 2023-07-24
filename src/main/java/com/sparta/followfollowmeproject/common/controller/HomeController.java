package com.sparta.followfollowmeproject.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

//    @GetMapping("/api/user/login-page")
//    public String loginPage() {
//        return "login";
//    }

    @GetMapping("/api/user/signup")
    public String showSignupPage() {
        return "signup";
    }

    @GetMapping("/new-post")
    public String newPostPage() {
        return "new_post";
    }

    @GetMapping("/detail-page")
    public String detailPage() {
        return "detail-page";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping("/post_edit")
    public String postEditPage() {
        return "post_edit";
    }

    @GetMapping("/userinfo")
    public String userinfoPage() {
        return "userinfo";
    }


    @GetMapping("/followlist")
    public String followlistPage() {
        return "followlist";
    }


}
