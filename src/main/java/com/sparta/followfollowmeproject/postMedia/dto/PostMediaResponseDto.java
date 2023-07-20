package com.sparta.followfollowmeproject.postMedia.dto;

import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.postMedia.entity.PostMedia;
import lombok.Getter;

@Getter
public class PostMediaResponseDto extends ApiResponseDto {
	private String mediaUrl;

	public PostMediaResponseDto(PostMedia postMedia) {
		this.mediaUrl = postMedia.getMdeiaUrl();
	}
}
