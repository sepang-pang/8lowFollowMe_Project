package com.sparta.followfollowmeproject.comment.repository;

import com.sparta.followfollowmeproject.comment.entity.Comment;
import com.sparta.followfollowmeproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long> {
<<<<<<< HEAD
	 List<Comment> findAllByPostOrderByCreatedAtDesc(Post post);
=======
	List<Comment> findAllByPostOrderByCreatedAtDesc(Post post);
>>>>>>> fad7d056e5a8782b5efe813358dfd2f6b3edfc58
}
