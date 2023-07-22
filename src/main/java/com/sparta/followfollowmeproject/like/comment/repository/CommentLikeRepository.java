package com.sparta.followfollowmeproject.like.comment.repository;

import java.util.Optional;

import com.sparta.followfollowmeproject.comment.entity.Comment;
import com.sparta.followfollowmeproject.like.comment.entity.CommentLike;
import com.sparta.followfollowmeproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
    Boolean existsByUserAndComment(User user, Comment comment);
}

