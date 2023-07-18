package com.sparta.followfollowmeproject.post.service;

import com.sparta.followfollowmeproject.post.dto.PostDTO;
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

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PostDTO getPostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.map(this::convertToDTO).orElse(null);
    }

    public PostDTO createPost(PostDTO postDTO) {
        Post post = convertToEntity(postDTO);
        Post savedPost = postRepository.save(post);
        return convertToDTO(savedPost);
    }

    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            BeanUtils.copyProperties(postDTO, post);
            Post updatedPost = postRepository.save(post);
            return convertToDTO(updatedPost);
        }
        return null;
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        return postDTO;
    }

    private Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        BeanUtils.copyProperties(postDTO, post);
        return post;
    }
}