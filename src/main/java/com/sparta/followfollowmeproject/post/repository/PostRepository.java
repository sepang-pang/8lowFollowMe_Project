package com.sparta.followfollowmeproject.post.repository;

import com.sparta.followfollowmeproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
