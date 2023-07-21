package com.sparta.followfollowmeproject.like.post.service;

import java.util.Optional;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sun.jdi.request.DuplicateRequestException;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.like.post.entity.PostLike;
import com.sparta.followfollowmeproject.user.entity.User;
import com.sparta.followfollowmeproject.like.post.repository.PostLikeRepository;
import com.sparta.followfollowmeproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    // 좋아요
    @Override
    @Transactional
    public void likePost(Long id, User user) {
        Post post = findPost(id);

        if (postLikeRepository.existsByUserAndPost(user, post)) {
            throw new DuplicateRequestException("이미 좋아요 한 게시글 입니다.");
        } else {
            PostLike postLike = new PostLike(user, post);
            postLike.increaseLikeCount();  // 좋아요 카운터를 증가시킵니다
            postLikeRepository.save(postLike);
        }
    }

    // 좋아요 취소
    @Override
    @Transactional
    public void deleteLikePost(Long id, User user) {
        Post post = findPost(id);
        Optional<PostLike> postLikeOptional = postLikeRepository.findByUserAndPost(user, post);
        if (postLikeOptional.isPresent()) {
            PostLike postLike = postLikeOptional.get();
            postLike.decreaseLikeCount();  // 좋아요 카운터를 감소시킵니다
            postLikeRepository.save(postLike);  // 좋아요 카운터가 감소한 상태를 저장합니다
            postLikeRepository.delete(postLike);
        } else {
            throw new IllegalArgumentException("해당 게시글에 취소할 좋아요가 없습니다.");
        }
    }

    // 게시글 찾기
    @Override
    public Post findPost(long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }

    // 게시글 작성자와 요청자가 같은지 확인
    @Override
    public boolean isOwnPost(Long id, User user) {
        Post post = findPost(id);
        return post.getUser().equals(user);
    }
}
