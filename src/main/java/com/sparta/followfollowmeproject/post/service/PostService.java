package com.sparta.followfollowmeproject.post.service;


import com.sparta.followfollowmeproject.comment.dto.CommentResponseDto;
import com.sparta.followfollowmeproject.comment.entity.Comment;
import com.sparta.followfollowmeproject.comment.service.CommentService;
import com.sparta.followfollowmeproject.post.dto.PostRequestDto;
import com.sparta.followfollowmeproject.post.dto.PostResponseDto;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.post.repository.PostRepository;
import com.sparta.followfollowmeproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentService commentService;

    public List<PostResponseDto> getAllPosts() {
//        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
//
//        return posts.stream().map(PostResponseDto::new).toList();

        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(
                (Post post) -> new PostResponseDto(post, commentService.getCommentsByPostId(post.getId()))
        ).toList();
    }

    public PostResponseDto getPostById(Long id) {
        Post post = findPost(id);
//        if (optionalPost.isPresent()) {
//            Post post = optionalPost.get();
//            return new PostResponseDto(post);
//        } else {
//            return null;
//        }
        List<CommentResponseDto> commentList = commentService.getCommentsByPostId(post.getId());
        return new PostResponseDto(post, commentList);
    }


    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    // delete 를 참고하여 수정하기
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.update(requestDto);
            Post updatedPost = postRepository.save(post);
            return new PostResponseDto(updatedPost);
        } else {
            return null;
        }
    }

    public void deletePost(Long id, User user) {
        Post post = findPost(id);

        if(!post.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("본인이 아닙니다");
        }
        postRepository.delete(post);
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.")
        );
    }


}
