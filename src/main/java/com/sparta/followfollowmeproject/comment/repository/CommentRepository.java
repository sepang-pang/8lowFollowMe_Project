package com.sparta.followfollowmeproject.comment.repository;

import com.sparta.followfollowmeproject.comment.entity.Comment;
import com.sparta.followfollowmeproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long> {
	public List<Comment> findAllByPostOrderByCreatedAtDesc(Post post);
}
