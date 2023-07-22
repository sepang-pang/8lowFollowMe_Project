package com.sparta.followfollowmeproject.post.repository;

import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findByUserInOrderByCreatedAtDesc(List<User> followingUsers);

    List<Post> findByIsNoticeTrue();
    Optional<Post> findByIdAndIsNoticeTrue(Long id);
}
