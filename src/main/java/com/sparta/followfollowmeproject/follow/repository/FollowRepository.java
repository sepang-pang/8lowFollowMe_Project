package com.sparta.followfollowmeproject.follow.repository;

import com.sparta.followfollowmeproject.follow.entity.Follow;
import com.sparta.followfollowmeproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
	List<Follow> findAllByFollower(User follower);

	Follow findByFollowerAndFollowing(User follower, User user);

}
