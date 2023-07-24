package com.sparta.followfollowmeproject.media.dto;

import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.media.entity.PostMedia;
import lombok.Getter;

@Getter
public class PostMediaResponseDto extends ApiResponseDto {
	private Long id;
	private String mediaUrl;

	public PostMediaResponseDto(PostMedia postMedia) {
		this.id = postMedia.getId();
		this.mediaUrl = postMedia.getMediaUrl();
	}
}