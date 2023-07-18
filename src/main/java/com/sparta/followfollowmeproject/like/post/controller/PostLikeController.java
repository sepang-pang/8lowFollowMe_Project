package com.sparta.followfollowmeproject.like.post.controller;

import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.common.security.UserDetailsImpl;
import com.sparta.followfollowmeproject.like.post.service.PostLikeService;
import com.sparta.followfollowmeproject.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/posts/{id}/like")
    public ResponseEntity<ApiResponseDto> likePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        if(postLikeService.isOwnPost(id, userDetails.getUser())) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("자신의 게시글에는 좋아요를 누를 수 없습니다.", HttpStatus.BAD_REQUEST.value()));
        }
        try {
            postLikeService.likePost(id, userDetails.getUser());
        } catch (DuplicateRequestException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto("게시글 좋아요 성공", HttpStatus.ACCEPTED.value()));
    }

    @DeleteMapping("/posts/{id}/like")
    public ResponseEntity<ApiResponseDto> deleteLikePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        try {
            postLikeService.deleteLikePost(id, userDetails.getUser());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto("게시글 좋아요 취소 성공", HttpStatus.ACCEPTED.value()));
    }
}