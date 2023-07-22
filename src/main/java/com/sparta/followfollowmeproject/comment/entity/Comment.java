package com.sparta.followfollowmeproject.comment.entity;

import com.sparta.followfollowmeproject.admin.entity.Admin;
import com.sparta.followfollowmeproject.comment.dto.CommentRequestDto;
import com.sparta.followfollowmeproject.common.entity.Timestamped;
import com.sparta.followfollowmeproject.like.comment.entity.CommentLike;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "comments")
@NoArgsConstructor
public class Comment  extends Timestamped {

	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 게시글
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "postId", nullable = false)
	private Post post;

	// 내용
	@Column(name = "content", nullable = false, length = 100)
	private String content;

	// 유저
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	// 대댓글
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "parent_comment_id")
	private Comment parentComment;

	// 좋아요
	@Column(name = "likeCnt")
	private long likeCnt;

	// 좋아요 리스트
	@OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE) // 댓글 좋아요
	private List<CommentLike> commentLikes = new ArrayList<>();

	public Comment(Post post, CommentRequestDto requestDto, User user) {
		this.post = post;
		this.content = requestDto.getContent();
		this.user = user;
	}

	public Comment(Post post, CommentRequestDto requestDto, User user, Comment parentComment) {
		this.post = post;
		this.content = requestDto.getContent();
		this.user = user;
		this.parentComment = parentComment;
	}

	public void update(CommentRequestDto requestDto) {
		this.content = requestDto.getContent();
	}

	public Comment(String content) {
		this.content = content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
