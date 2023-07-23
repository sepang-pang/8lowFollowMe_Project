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

  /*
    return "followlist";
    return "follow";
    return "userinfo";
    return "post_edit";
    return "new_post";
    return "detail-page";
    return "profile";
    */


}
