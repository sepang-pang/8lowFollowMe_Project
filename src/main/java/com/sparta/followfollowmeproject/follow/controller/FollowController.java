package com.sparta.followfollowmeproject.follow.controller;

import com.sparta.followfollowmeproject.common.security.UserDetailsImpl;
import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.follow.dto.FollowingListDto;
import com.sparta.followfollowmeproject.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
public class FollowController {
	private final FollowService followService;

	// 로그인한 유저의 모든 팔로잉 조회
	@GetMapping("/following")
	public ResponseEntity<List<FollowingListDto>> getFollowingList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		List<FollowingListDto> followingList = followService.getFollowingList(userDetails.getUser());
		return ResponseEntity.ok().body(followingList);
	}

	// 선택한 user 팔로우
	@PostMapping("/{followingId}/follow")
	public ResponseEntity<ApiResponseDto> follow(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long followingId) {
		followService.follow(userDetails.getUser(), followingId);
		return ResponseEntity.ok().body(new ApiResponseDto("팔로우가 완료되었습니다.", HttpStatus.OK.value()));
	}

	// 선택한 user 언팔로우
	@DeleteMapping("/{followingId}/unfollow")
	public ResponseEntity<ApiResponseDto> unfollow(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long followingId) {
		followService.unfollow(userDetails.getUser(), followingId);
		return ResponseEntity.ok().body(new ApiResponseDto("언팔로우가 완료되었습니다.", HttpStatus.OK.value()));
	}
}
