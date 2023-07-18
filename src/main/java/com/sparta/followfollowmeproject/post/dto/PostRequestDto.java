package com.sparta.followfollowmeproject.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
    private String userId;
}
