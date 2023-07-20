package com.sparta.followfollowmeproject.admin.user.management.controller;


import com.sparta.followfollowmeproject.admin.user.management.dto.UserManagementRequestDto;
import com.sparta.followfollowmeproject.admin.user.management.dto.UserManagementResponseDto;
import com.sparta.followfollowmeproject.admin.user.management.service.UserManagementService;
import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.user.entity.UserRoleEnum;
import com.sparta.followfollowmeproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j(topic = "UserManagementController Log")
@RestController
@RequiredArgsConstructor
@Secured(UserRoleEnum.Authority.ADMIN)
@RequestMapping("/api")
public class UserManagementController {

    private final UserManagementService userManagementService;

    // 회원 조회
    @GetMapping("/users")
    List<UserManagementResponseDto> getUserList() {
        return userManagementService.getUserList();
    }

    // 회원 삭제
    @DeleteMapping("/users")
    public ResponseEntity<ApiResponseDto> deleteUser(@RequestParam("username") String username) {
        return userManagementService.deleteUser(username);
    }

    // 운영진 승격
    @PutMapping("/users")
    public ResponseEntity<ApiResponseDto> promotionUserRole(@RequestParam("username") String username,
                                                                    @RequestBody UserManagementRequestDto requestDto) {
        return userManagementService.promotionUserRole(username, requestDto);
    }

    // 회원 정보 수정 -> 프로필이 완성되면 그것과 연계하여 구현 예정

    // 회원 차단 ->
}
