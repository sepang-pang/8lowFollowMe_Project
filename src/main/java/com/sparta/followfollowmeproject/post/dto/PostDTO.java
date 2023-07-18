package com.sparta.followfollowmeproject.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
