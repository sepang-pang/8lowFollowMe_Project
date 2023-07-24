package com.sparta.followfollowmeproject.like.comment.service;

import com.sparta.followfollowmeproject.advice.custom.CommentNotFoundException;
import com.sparta.followfollowmeproject.advice.custom.LikeNotFoundException;
import com.sparta.followfollowmeproject.advice.custom.PostNotFoundException;
import com.sparta.followfollowmeproject.comment.entity.Comment;
import com.sparta.followfollowmeproject.comment.repository.CommentRepository;
import com.sparta.followfollowmeproject.like.comment.entity.CommentLike;
import com.sparta.followfollowmeproject.like.comment.repository.CommentLikeRepository;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.post.repository.PostRepository;
import com.sparta.followfollowmeproject.user.entity.User;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    @Transactional // 좋아요
    public void likeComment(Long postId, Long commentId, User user) throws CommentNotFoundException {
        findPost(postId);
        Comment comment = findComment(commentId);

        if (commentLikeRepository.existsByUserAndComment(user, comment)) {
            throw new DuplicateRequestException("이미 좋아요 한 댓글 입니다.");
        } else {
            CommentLike commentLike = new CommentLike(user, comment);
            commentLikeRepository.save(commentLike);
        }
    }

    @Transactional // 좋아요 취소
    public void deleteLikeComment(Long postId, Long commentId, User user) throws CommentNotFoundException {
        findPost(postId);
        Comment comment = findComment(commentId);
        Optional<CommentLike> commentLikeOptional = commentLikeRepository.findByUserAndComment(user, comment);
        if (commentLikeOptional.isPresent()) {
            commentLikeRepository.delete(commentLikeOptional.get());
        } else {
            throw new LikeNotFoundException("해당 댓글에 취소할 좋아요가 없습니다.");
        }
    }

    //------- 공통 메서드 -------//

    // id에 따른 댓글 찾기
    private Comment findComment(Long commentId) throws CommentNotFoundException {
        return commentRepository.findById(commentId).orElseThrow(() ->
                // 댓글이 존재하지 않을 경우 예외 처리
                new CommentNotFoundException("선택한 댓글은 존재하지 않습니다.")
        );
    }

    // id에 따른 게시글 찾기
    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new PostNotFoundException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}
