package com.sparta.followfollowmeproject.comment.controller;

import com.sparta.followfollowmeproject.common.security.UserDetailsImpl;
import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.comment.dto.CommentRequestDto;
import com.sparta.followfollowmeproject.comment.dto.CommentResponseDto;
import com.sparta.followfollowmeproject.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class CommentController {
	private final CommentService commentService;

	// 선택한 게시글에 대한 모든 댓글 조회
	@GetMapping("/{postId}/comments")
	public ResponseEntity<List<CommentResponseDto>> getCommentsByPostId(@PathVariable Long postId) {
		List<CommentResponseDto> responseDtos = commentService.getCommentsByPostId(postId);
		return ResponseEntity.ok().body(responseDtos);
	}

	// 댓글 작성
	@PostMapping("/{postId}/comments")
	public ResponseEntity<ApiResponseDto> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		CommentResponseDto responseDto = commentService.createComment(postId, requestDto, userDetails.getUser());
		return ResponseEntity.ok().body(responseDto);
	}

	// 선택한 댓글 수정
	@PutMapping("/{postId}/comments/{commentId}")
	public ResponseEntity<ApiResponseDto> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		CommentResponseDto responseDto = commentService.updateComment(postId, commentId, requestDto, userDetails.getUser());
		return ResponseEntity.ok().body(responseDto);
	}

	// 선택한 댓글 삭제
	@DeleteMapping("/{postId}/comments/{commentId}")
	public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long postId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		commentService.deleteComment(postId, commentId, userDetails.getUser());
		return ResponseEntity.ok().body(new ApiResponseDto("해당 댓글의 삭제를 완료했습니다.", HttpStatus.OK.value()));
	}
}
