package com.sparta.followfollowmeproject.post.service;


import com.sparta.followfollowmeproject.comment.dto.CommentResponseDto;
import com.sparta.followfollowmeproject.comment.service.CommentService;
import com.sparta.followfollowmeproject.follow.entity.Follow;
import com.sparta.followfollowmeproject.follow.repository.FollowRepository;
import com.sparta.followfollowmeproject.post.dto.PostRequestDto;
import com.sparta.followfollowmeproject.post.dto.PostResponseDto;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.post.repository.PostRepository;
import com.sparta.followfollowmeproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentService commentService;
    private final FollowRepository followRepository;

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(
                (Post post) -> new PostResponseDto(post, commentService.getCommentsByPostId(post.getId()))
        ).toList();
    }

    public PostResponseDto getPostById(Long id) {
        Post post = findPost(id);
        List<CommentResponseDto> commentList = commentService.getCommentsByPostId(post.getId());
        return new PostResponseDto(post, commentList);
    }


    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    // delete 를 참고하여 수정하기!
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {
        Post post = findPost(id);

        if (!post.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("본인이 아닙니다.");
        }

        post.update(requestDto);
        Post updatedPost = postRepository.save(post);
        return new PostResponseDto(updatedPost);
    }

    public void deletePost(Long id, User user) {
        Post post = findPost(id);

        if (!post.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("본인이 아닙니다");
        }
        postRepository.delete(post);
    }

    public Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.")
        );
    }


    // 팔로우 한 사용자의 게시글만 조회
    @Transactional(readOnly=true)
    public List<PostResponseDto> getFollowingPosts(User user) {
        // 로그인 한 사용자가 팔로우한 목록 가져오기
        List<Follow> follows = followRepository.findAllByFollower(user);
        List<User> followingUsers = follows.stream()
                .map(Follow::getFollowing).toList();

        // 팔로우 하는 유저들의 게시글 조회
        return postRepository.findByUserInOrderByCreatedAtDesc(followingUsers).stream().map(
                (Post post) -> new PostResponseDto(post, commentService.getCommentsByPostId(post.getId()))
        ).toList();
    }
}
