package com.sparta.followfollowmeproject.post.entity;

import com.sparta.followfollowmeproject.post.dto.PostRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Post(PostRequestDto requestDto) {
        this.title = getTitle();
        this.content = getContent();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
