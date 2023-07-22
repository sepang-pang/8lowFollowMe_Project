package com.sparta.followfollowmeproject.admin.user.management.dto;

import com.sparta.followfollowmeproject.user.entity.User;
import lombok.Getter;

@Getter
public class UserManagementResponseDto {
    private String username;
    private String email;
    private String role;

    public UserManagementResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getRole().getAuthority();
    }
}
