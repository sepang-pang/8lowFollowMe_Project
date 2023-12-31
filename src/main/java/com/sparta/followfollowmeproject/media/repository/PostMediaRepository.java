package com.sparta.followfollowmeproject.media.repository;

import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.media.entity.PostMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostMediaRepository extends JpaRepository<PostMedia, Long> {
	List<PostMedia> findAllByPostOrderByCreatedAt(Post post);
}