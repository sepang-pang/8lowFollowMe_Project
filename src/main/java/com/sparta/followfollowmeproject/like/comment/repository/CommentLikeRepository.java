package com.sparta.followfollowmeproject.like.comment.repository;

import java.util.List;
import java.util.Optional;

import com.sparta.followfollowmeproject.comment.entity.Comment;
import com.sparta.followfollowmeproject.like.comment.entity.CommentLike;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    // 유저와 댓글로 찾기
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
    // 유저와 댓글이 존재하는지 확인
    Boolean existsByUserAndComment(User user, Comment comment);
}

