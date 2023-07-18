package com.sparta.followfollowmeproject.comment.service;

import com.sparta.followfollowmeproject.comment.dto.CommentRequestDto;
import com.sparta.followfollowmeproject.comment.dto.CommentResponseDto;
import com.sparta.followfollowmeproject.comment.entity.Comment;
import com.sparta.followfollowmeproject.comment.repository.CommentRepository;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.post.repository.PostRepository;
import com.sparta.followfollowmeproject.user.entity.User;
import com.sparta.followfollowmeproject.user.entity.UserRoleEnum;
import com.sparta.followfollowmeproject.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	// 선택한 게시글에 대한 댓글 전체 조회
	public List<CommentResponseDto> getCommentsByPostId(Long postId) {
		return commentRepository.findAllByPostOrderByCreatedAtDesc(findPost(postId)).stream().map(CommentResponseDto::new).toList();
	}

	// 댓글 작성
	public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, User user) {
		User targetUser = findUser(user.getUserId());
		if (targetUser != null) {
			Post post = findPost(postId);
			Comment comment = new Comment(post, requestDto, user);
			Comment saveComment = commentRepository.save(comment);
			CommentResponseDto commentResponseDto = new CommentResponseDto(saveComment);
			return commentResponseDto;
		} else {
			throw new IllegalArgumentException("해당 사용자는 존재하지 않습니다.");
		}
	}

	// 선택한 댓글 수정
	@Transactional
	public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto, User user) {
		Comment comment = findComment(commentId);
		// postId 받은 것과 comment DB에 저장된 postId가 다를 경우 예외 처리
		if (postId != findComment(commentId).getPost().getPostId()) {
			throw new EntityNotFoundException("해당 페이지를 찾을 수 없습니다.");
		}
		// 다른 유저가 수정을 시도할 경우 예외 처리
		User targetUser = findUser(user.getUserId());
		if (targetUser != null) {
			// 게시글 작성자(post.user) 와 요청자(user) 가 같은지 또는 Admin 인지 체크 (아니면 예외발생)
			if (!(targetUser.getRole().equals(UserRoleEnum.ADMIN) || comment.getUser().equals(targetUser))) {
				throw new RejectedExecutionException("작성자만 수정할 수 있습니다.");
			}
		} else {
			throw new IllegalArgumentException("해당 사용자는 존재하지 않습니다.");
		}
		// 오류가 나지 않을 경우 해당 댓글 수정
		comment.update(requestDto);
		CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
		return commentResponseDto;

	}

	// 선택한 댓글 삭제
	public void deleteComment(Long postId, Long commentId, @AuthenticationPrincipal User user) {
		Comment comment = findComment(commentId);
		// postId 받은 것과 comment DB에 저장된 postId가 다를 경우 예외 처리
		if (postId != findComment(commentId).getPost().getPostId()) {
			throw new EntityNotFoundException("해당 페이지를 찾을 수 없습니다.");
		}
		// 다른 유저가 삭제를 시도할 경우 예외 처리
		User targetUser = findUser(user.getUserId());
		if (targetUser != null) {
			// 게시글 작성자(post.user) 와 요청자(user) 가 같은지 또는 Admin 인지 체크 (아니면 예외발생)
			if (!(targetUser.getRole().equals(UserRoleEnum.ADMIN) || comment.getUser().equals(targetUser))) {
				throw new RejectedExecutionException("작성자만 삭제할 수 있습니다.");
			}
		} else {
			throw new IllegalArgumentException("해당 사용자는 존재하지 않습니다.");
		}
		// 오류가 나지 않을 경우 해당 댓글 삭제
		commentRepository.delete(findComment(commentId));
	}

	// id에 따른 댓글 찾기
	private Comment findComment(Long commentId) {
		return commentRepository.findById(commentId).orElseThrow(() ->
				// 댓글이 존재하지 않을 경우 예외 처리
				new EntityNotFoundException("선택한 댓글은 존재하지 않습니다.")
		);
	}

	// id에 따른 게시글 찾기
	private Post findPost(Long postId) {
		return postRepository.findById(postId).orElseThrow(() ->
				new EntityNotFoundException("선택한 게시글은 존재하지 않습니다.")
		);
	}

	//userId로 User 찾기
	private User findUser(String userId) {
		return userRepository.findByUserId(userId).orElseThrow(() ->
				new IllegalArgumentException("선택한 유저는 존재하지 않습니다.")
		);
	}
}
