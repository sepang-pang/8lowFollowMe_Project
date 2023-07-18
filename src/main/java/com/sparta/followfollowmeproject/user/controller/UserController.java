package com.sparta.followfollowmeproject.user.controller;

import com.sparta.followfollowmeproject.advice.exception.RestApiException;
import com.sparta.followfollowmeproject.user.dto.SignupRequestDto;
import com.sparta.followfollowmeproject.user.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j(topic = "UserController 로그")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/user/signup")
    public RestApiException signup(@RequestBody @Valid SignupRequestDto requestDto, BindingResult bindingResult){
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return new RestApiException("회원가입 실패", HttpStatus.BAD_REQUEST.value());
        }
        return userService.signup(requestDto);
    }

}