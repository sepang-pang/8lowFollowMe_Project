package com.sparta.followfollowmeproject.comment.repository;

import com.sparta.followfollowmeproject.comment.entity.Comment;
import com.sparta.followfollowmeproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository extends JpaRepository<Comment, Long> {
	public List<Comment> findAllByPostOrderByCreatedAtDesc(Post post);
}
