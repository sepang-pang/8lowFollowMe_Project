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
@RequestMapping("/api/admin")
public class UserManagementController {

    private final UserManagementService userManagementService;

    // 회원 조회
    @GetMapping("/users")
    List<UserManagementResponseDto> getUserList() {
        return userManagementService.getUserList();
    }

    // 운영진 승격
    @PutMapping("/users")
    public ResponseEntity<ApiResponseDto> promotionUserRole(@RequestParam("username") String username,
                                                                    @RequestBody UserManagementRequestDto requestDto) {
        return userManagementService.promotionUserRole(username, requestDto);
    }

    // 회원 차단
    @PutMapping("/users/block")
    public ResponseEntity<ApiResponseDto> blockUser(@RequestParam("username") String username) {
        return userManagementService.blockUser(username);
    }

    // 회원 차단 해제
    @PutMapping("/users/unblock")
    public ResponseEntity<ApiResponseDto> unblockUser(@RequestParam("username") String username) {
        return userManagementService.unblockUser(username);
    }

    // 회원 삭제
    @DeleteMapping("/users")
    public ResponseEntity<ApiResponseDto> deleteUser(@RequestParam("username") String username) {
        return userManagementService.deleteUser(username);
    }
}
