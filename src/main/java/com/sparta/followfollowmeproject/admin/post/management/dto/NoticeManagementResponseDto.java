package com.sparta.followfollowmeproject.admin.post.management.dto;

import com.sparta.followfollowmeproject.admin.entity.Notice;
import com.sparta.followfollowmeproject.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeManagementResponseDto {

    private Long id;
    private String title;
    private String content;
    private boolean isPinned;
    private LocalDateTime createdAt;

    public NoticeManagementResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.isPinned = post.getIsPinned();
        this.createdAt = post.getCreatedAt();
    }
}
