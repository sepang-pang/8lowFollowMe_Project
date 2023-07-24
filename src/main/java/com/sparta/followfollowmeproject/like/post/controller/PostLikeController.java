package com.sparta.followfollowmeproject.like.post.controller;

import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.common.security.UserDetailsImpl;
import com.sparta.followfollowmeproject.like.post.service.PostLikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService postLikeService;

    // 게시글 좋아요
    @PostMapping("/posts/{id}/like")
    public ResponseEntity<ApiResponseDto> likePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        postLikeService.likePost(id, userDetails.getUser()); // 수정된 부분
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto("게시글 좋아요 성공", HttpStatus.ACCEPTED.value()));
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/posts/{id}/like")
    public ResponseEntity<ApiResponseDto> deleteLikePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        postLikeService.deleteLikePost(id, userDetails.getUser()); // 수정된 부분
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto("게시글 좋아요 취소 성공", HttpStatus.ACCEPTED.value()));
    }
}