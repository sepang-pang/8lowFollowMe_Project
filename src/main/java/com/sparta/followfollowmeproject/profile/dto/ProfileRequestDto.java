package com.sparta.followfollowmeproject.profile.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileRequestDto {

    private String nickName;
    private String intro;
    private int age;
    private String mediaUrl;

}
