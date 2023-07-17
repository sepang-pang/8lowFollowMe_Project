package com.sparta.followfollowmeproject.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {
	@NotBlank
	private String content;
}
