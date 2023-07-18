package com.sparta.followfollowmeproject.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
    private String title;
    private String content;
    private String userId;
}
