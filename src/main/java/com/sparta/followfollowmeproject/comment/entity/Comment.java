package com.sparta.followfollowmeproject.comment.entity;

import com.sparta.followfollowmeproject.admin.entity.Admin;
import com.sparta.followfollowmeproject.comment.dto.CommentRequestDto;
import com.sparta.followfollowmeproject.common.entity.Timestamped;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comments")
@NoArgsConstructor
public class Comment  extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postId", nullable = false)
	private Post post;

	@Column(name = "content", nullable = false, length = 100)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private User user;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "admin_id", nullable = false)
//	private Admin admin;

	@Column(name = "likeCnt")
	private long likeCnt;

	public Comment(Post post, CommentRequestDto requestDto, User user) {
		this.post = post;
		this.content = requestDto.getContent();
		this.user = user;
	}

	public void update(CommentRequestDto requestDto) {
		this.content = requestDto.getContent();
	}
}
