package com.sparta.followfollowmeproject.follow.dto;

import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowingListDto extends ApiResponseDto {
	private String username;
}
