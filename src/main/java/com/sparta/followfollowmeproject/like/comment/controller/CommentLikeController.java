package com.sparta.followfollowmeproject.like.comment.controller;

import com.sparta.followfollowmeproject.advice.custom.CommentNotFoundException;
import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.common.security.UserDetailsImpl;
import com.sparta.followfollowmeproject.like.comment.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;
    @PostMapping("/{postId}/comments/{commentId}/like") // 댓글 좋아요
    public ResponseEntity<ApiResponseDto> likeComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @PathVariable Long commentId) throws CommentNotFoundException {
        commentLikeService.likeComment(postId, commentId, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto("댓글 좋아요 성공", HttpStatus.ACCEPTED.value()));
    }

    @DeleteMapping("/{postId}/comments/{commentId}/deleteLike") // 댓글 좋아요 취소
    public ResponseEntity<ApiResponseDto> deleteLikeComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @PathVariable Long commentId) throws CommentNotFoundException {
        commentLikeService.deleteLikeComment(postId, commentId, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponseDto("댓글 좋아요 취소 성공", HttpStatus.ACCEPTED.value()));
    }
}
