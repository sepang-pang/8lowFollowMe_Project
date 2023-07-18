package com.sparta.followfollowmeproject.post.dto;

import com.sparta.followfollowmeproject.comment.dto.CommentResponseDto;
import com.sparta.followfollowmeproject.post.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> commentList;


    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = new ArrayList<>();
    }

    public PostResponseDto(Post post, List<CommentResponseDto> commentList) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = commentList.stream().toList();
    }
}
