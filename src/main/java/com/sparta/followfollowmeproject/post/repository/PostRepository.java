package com.sparta.followfollowmeproject.post.repository;

import com.sparta.followfollowmeproject.post.entity.Post;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
}
