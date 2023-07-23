package com.sparta.followfollowmeproject.profile.dto;

import com.sparta.followfollowmeproject.profile.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileResponseDto {

    private String nickName;
    private String intro;
    private int age;
    private String photoUrl;

    public ProfileResponseDto(Profile profile) {
        this.nickName = profile.getNickName();
        this.intro = profile.getIntro();
        this.age = profile.getAge();
        this.photoUrl = profile.getPhotoUrl();
    }
}