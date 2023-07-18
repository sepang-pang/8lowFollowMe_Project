package com.sparta.followfollowmeproject.follow.entity;

import com.sparta.followfollowmeproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "follow")
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "followerId")
	private User follower;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "followingId")
	private User following;

	public Follow(User follower, User following) {
		this.follower = follower;
		this.following = following;
	}
}
