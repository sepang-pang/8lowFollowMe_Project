package com.sparta.followfollowmeproject.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {
	// 내용
	@NotBlank
	private String content;
	// 대댓글
	private Long parentId;
}
