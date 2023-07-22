package com.sparta.followfollowmeproject.like.post.dto;

import com.sparta.followfollowmeproject.post.dto.PostResponseDto;
import lombok.Getter;
import java.util.List;

@Getter
public class PostListResponseDto {

    // 게시글 리스트
    private List<PostResponseDto> postsList;

    public PostListResponseDto(List<PostResponseDto> postList) {
        this.postsList = postList;
    }
}
