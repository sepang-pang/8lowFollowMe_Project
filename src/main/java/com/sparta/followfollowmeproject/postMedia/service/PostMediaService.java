package com.sparta.followfollowmeproject.postMedia.service;

import com.sparta.followfollowmeproject.advice.custom.MediaMismatchException;
import com.sparta.followfollowmeproject.advice.custom.MediaNotFoundException;
import com.sparta.followfollowmeproject.advice.custom.MediaUploadException;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.post.service.PostService;
import com.sparta.followfollowmeproject.postMedia.dto.PostMediaResponseDto;
import com.sparta.followfollowmeproject.postMedia.entity.PostMedia;
import com.sparta.followfollowmeproject.postMedia.repository.PostMediaRepository;
import com.sparta.followfollowmeproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostMediaService {

	private final S3UploadService s3UploadService;
	private final PostMediaRepository postMediaRepository;
	private final PostService postService;

	@Transactional(readOnly = true)
	// 선택한 게시글의 미디어 전체 조회
	public List<PostMediaResponseDto> getMediaByPostId(Long postId) {
		return postMediaRepository.findAllByPostOrderByCreatedAt(postService.findPost(postId)).stream().map(PostMediaResponseDto::new).toList();
	}

	// 미디어 S3 업로드 및 URL DB 저장
	@Transactional
	public PostMediaResponseDto uploadMedia(Long postId, MultipartFile multipartFile, User user) throws IOException {
		Post post = postService.findPost(postId);

		// post의 작성자가 맞는지 확인하기
		if (!post.getUser().getUsername().equals(user.getUsername())) {
			throw new MediaUploadException("게시글의 작성자만 미디어를 업로드할 수 있습니다.");
		}
		// 업로드 할 파일을 선택했는지 확인하기
		else if (multipartFile.getSize() == 0) {
			throw new MediaUploadException("업로드할 미디어를 선택해주세요");
		}

		else {
			// AWS S3 저장
			String mediaUrl = s3UploadService.uploadFile(multipartFile);
			// DB 저장
			PostMedia postMedia = new PostMedia(post, mediaUrl);
			PostMedia savedPostMedia = postMediaRepository.save(postMedia);
			return new PostMediaResponseDto(savedPostMedia);
		}
	}

	// 미디어 S3 삭제 및 재업로드 및 URL DB 수정
	@Transactional
	public PostMediaResponseDto updateMedia(Long postId, Long mediaId, MultipartFile multipartFile, User user) throws IOException {
		Post post = postService.findPost(postId);
		PostMedia postMedia = findById(mediaId);

		// 받은 postId와 media의 post가 일치하는지 확인하기
		if (!postId.equals(postMedia.getPost().getId())) {
			throw new MediaMismatchException("해당 게시글의 미디어가 아닙니다.");
		}
		// post의 작성자가 맞는지 확인하기
		else if (!post.getUser().getUsername().equals(user.getUsername())) {
			throw new AccessDeniedException("게시글의 작성자만 미디어를 수정할 수 있습니다.");
		}
		// 업로드 할 파일을 선택했는지 확인하기
		else if (multipartFile.getSize() == 0) {
			throw new MediaNotFoundException("수정할 미디어를 선택해주세요");
		}

		else {
			// AWS S3 삭제
			String deletedMediaUrl = postMedia.getMediaUrl();
			s3UploadService.deleteFile(deletedMediaUrl);
			// AWS S3 저장
			String updatedMediaUrl = s3UploadService.uploadFile(multipartFile);
			// DB 수정
			postMedia.update(updatedMediaUrl);
			PostMedia updatedPostMedia = postMediaRepository.save(postMedia);
			return new PostMediaResponseDto(updatedPostMedia);
		}
	}

	// 미디어 S3 삭제 및 URL DB 삭제
	@Transactional
	public void deleteMedia(Long postId, Long mediaId, User user) {
		Post post = postService.findPost(postId);
		PostMedia postMedia = findById(mediaId);

		// 받은 postId와 media의 post가 일치하는지 확인하기
		if (!postId.equals(postMedia.getPost().getId())) {
			throw new MediaMismatchException("해당 게시글의 미디어가 아닙니다.");
		}
		// post의 작성자가 맞는지 확인하기
		else if (!post.getUser().getUsername().equals(user.getUsername())) {
			throw new AccessDeniedException("게시글의 작성자만 미디어를 삭제할 수 있습니다.");
		}

		else {
			// AWS S3 삭제
			String mediaUrl = postMedia.getMediaUrl();
			s3UploadService.deleteFile(mediaUrl);
			// DB 삭제
			postMediaRepository.delete(postMedia);
		}
	}

	// mediaId로 postMedia 찾기
	public PostMedia findById(Long mediaId) {
		return postMediaRepository.findById(mediaId).orElseThrow(
				() -> new MediaNotFoundException("해당 미디어를 찾을 수 없습니다.")
		);
	}
}