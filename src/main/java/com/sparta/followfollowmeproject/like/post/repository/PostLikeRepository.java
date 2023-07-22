package com.sparta.followfollowmeproject.like.post.repository;


import com.sparta.followfollowmeproject.like.post.entity.PostLike;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByUserAndPost(User user, Post post);
    Boolean existsByUserAndPost(User user, Post post);
}