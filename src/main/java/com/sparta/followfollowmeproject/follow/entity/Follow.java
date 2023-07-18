package com.sparta.followfollowmeproject.follow.entity;

import com.sparta.followfollowmeproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "follow", uniqueConstraints = {
		@UniqueConstraint(name = "UniqueFollowerUser", columnNames = {"followerId", "followingId"})
}) // 동일한 팔로우 관계가 두 번 이상 등록되지 않도록 하는 설정
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
