package com.sparta.followfollowmeproject.profile.controller;

import com.sparta.followfollowmeproject.common.security.UserDetailsImpl;
import com.sparta.followfollowmeproject.profile.dto.ProfileRequestDto;
import com.sparta.followfollowmeproject.profile.dto.ProfileResponseDto;
import com.sparta.followfollowmeproject.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j(topic = "ProfileController 관련 로그")
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/profile")
    private ProfileResponseDto createProfile(@RequestBody ProfileRequestDto profileRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return profileService.createProfile(profileRequestDto, userDetails.getUser());
    }

    @GetMapping("/profile")
    private List<ProfileResponseDto> getProfile(@RequestParam("username")String username) {
        return profileService.getProfile(username);
    }

    @PutMapping("/profile")
    private ProfileResponseDto updateProfile(@RequestBody ProfileRequestDto profileRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return profileService.updateProfile(profileRequestDto, userDetails.getUser());
    }
}
