package com.sparta.followfollowmeproject.post.service;


import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.post.repository.PostRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {

        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            BeanUtils.copyProperties(updatedPost, post, "id", "createdAt");
            return postRepository.save(post);
        }
        return null;
    }

    public void deletePost(Long id) {

        postRepository.deleteById(id);
    }


    }
