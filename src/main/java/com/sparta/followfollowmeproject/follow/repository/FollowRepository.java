package com.sparta.followfollowmeproject.follow.repository;

import com.sparta.followfollowmeproject.follow.entity.Follow;
import com.sparta.followfollowmeproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
	List<Follow> findAllByFollower(User follower);

	Follow findByFollowerAndFollowing(User follower, User user);

}
