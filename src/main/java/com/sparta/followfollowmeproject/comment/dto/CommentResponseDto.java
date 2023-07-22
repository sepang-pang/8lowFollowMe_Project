package com.sparta.followfollowmeproject.comment.dto;

import com.sparta.followfollowmeproject.comment.entity.Comment;
import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto extends ApiResponseDto {
	private Long id;
	private String username;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private String content;


	public CommentResponseDto(Comment comment) {
		this.id = comment.getId();
		this.username = comment.getUser().getUsername();
		this.createdAt = comment.getCreatedAt();
		this.modifiedAt = comment.getModifiedAt();
		this.content = comment.getContent();
	}
}
