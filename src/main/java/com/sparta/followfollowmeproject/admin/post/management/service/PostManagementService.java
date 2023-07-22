package com.sparta.followfollowmeproject.admin.post.management.service;

import com.sparta.followfollowmeproject.post.dto.PostRequestDto;
import com.sparta.followfollowmeproject.post.dto.PostResponseDto;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PostManagementService Log")
public class PostManagementService {
    private final PostRepository postRepository;

    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    public PostResponseDto getPostById(Long postId) {
        Post findPost = findPost(postId);
        return new PostResponseDto(findPost);
    }
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto) {
        Post findPost = findPost(postId);
        findPost.update(requestDto);
        return new PostResponseDto(findPost);
    }

    public void deletePost(Long postId) {
        Post findPost = findPost(postId);
        postRepository.delete(findPost);
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글은 존재하지 않습니다.")
        );
    }
}



