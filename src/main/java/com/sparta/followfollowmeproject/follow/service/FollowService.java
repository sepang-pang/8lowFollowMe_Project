package com.sparta.followfollowmeproject.follow.service;

import com.sparta.followfollowmeproject.advice.custom.NotExistException;
import com.sparta.followfollowmeproject.advice.custom.SelfFollowNotAllowedException;
import com.sparta.followfollowmeproject.advice.custom.UserNotFoundException;
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
	@Transactional(readOnly=true)
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
	public void follow(User follower, Long followingId) {
		if (follower.getId().equals(followingId)) {
			throw new SelfFollowNotAllowedException("본인은 팔로우할 수 없습니다.");
		} else {
			Follow follow = new Follow(follower, findUser(followingId));
			followRepository.save(follow);
		}
	}

	// 언팔로우
	@Transactional
	public void unfollow(User follower, Long followingId) {
		Follow follow = followRepository.findByFollowerAndFollowing(follower, findUser(followingId));
		if (follow == null) {
			throw new IllegalArgumentException("해당 유저를 팔로우하지 않으셨습니다.");
		} else {
			followRepository.delete(follow);
		}
	}

	// user id로 User 찾기
	private User findUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(() ->
				new UserNotFoundException("선택한 유저는 존재하지 않습니다.")
		);
	}
}
