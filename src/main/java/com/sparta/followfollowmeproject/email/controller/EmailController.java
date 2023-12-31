package com.sparta.followfollowmeproject.email.controller;

import jakarta.mail.MessagingException;
import com.sparta.followfollowmeproject.email.dto.EmailAuthRequest;
import com.sparta.followfollowmeproject.email.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
public class EmailController {
    private final EmailService emailService;

    public EmailController(final EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/signin/email-verification")
    public String mailVerification(@RequestBody EmailAuthRequest request) throws MessagingException, UnsupportedEncodingException {
        return emailService.sendEmail(request.email());
    }
}
