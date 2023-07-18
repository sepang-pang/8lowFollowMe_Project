package com.sparta.followfollowmeproject.follow.service;

import com.sparta.followfollowmeproject.follow.repository.FollowRepository;
import com.sparta.followfollowmeproject.user.entity.User;
import com.sparta.followfollowmeproject.user.repository.UserRepository;
import com.sparta.followfollowmeproject.follow.dto.FollowingListDto;
import com.sparta.followfollowmeproject.follow.entity.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

	private final FollowRepository followRepository;
	private final UserRepository userRepository;

	// 팔로잉 목록 조회
	public List<FollowingListDto> getFollowingList(User follower) {
		List<Follow> follows = followRepository.findAllByFollower(follower);
		List<FollowingListDto> followingList = new ArrayList<>();
		for (Follow follow : follows) {
			FollowingListDto dto = new FollowingListDto();
			dto.setUsername(follow.getFollowing().getUsername());
			followingList.add(dto);
		}
		return followingList;
	}

	// 팔로우
	@Transactional
	public void follow(User follower, Long followeeId) {
		Follow follow = new Follow(follower, findUser(followeeId));
		followRepository.save(follow);
	}

	// 언팔로우
	@Transactional
	public void unfollow(User follower, Long followeeId) {
		Follow follow = followRepository.findByFollowerAndFollowing(follower, findUser(followeeId));
		if (follow != null) {
			followRepository.delete(follow);
		}
		if (follow == null) {
			throw new IllegalArgumentException("해당 유저를 팔로우하지 않으셨습니다.");
		}
	}

	// user id로 User 찾기
	private User findUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(() ->
				new IllegalArgumentException("선택한 유저는 존재하지 않습니다.")
		);
	}
}
