package com.sparta.followfollowmeproject.postMedia.service;

import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.post.service.PostService;
import com.sparta.followfollowmeproject.postMedia.dto.PostMediaResponseDto;
import com.sparta.followfollowmeproject.postMedia.entity.PostMedia;
import com.sparta.followfollowmeproject.postMedia.repository.PostMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostMediaService {

	private final PostMediaRepository postMediaRepository;
	private final PostService postService;

	@Transactional(readOnly = true)
	// 선택한 게시글의 미디어 조회
	public List<PostMediaResponseDto> getMediaByPostId(Long postId) {
		return postMediaRepository.findAllByPostOrderByCreatedAtDesc(postService.findPost(postId)).stream().map(PostMediaResponseDto::new).toList();
	}

	// DB 업로드
	@Transactional
	public PostMediaResponseDto uploadUrl(Long postId,String mediaUrl) {
		Post post = postService.findPost(postId);
		PostMedia postMedia = new PostMedia(post, mediaUrl);
		PostMedia savedPostMedia = postMediaRepository.save(postMedia);
		return new PostMediaResponseDto(savedPostMedia);
	}

	// DB 수정
	public PostMediaResponseDto updateUrl(Long postId, Long mediaId, String updatedMediaUrl) {
		PostMedia postMedia = findById(mediaId);
		postMedia.update(updatedMediaUrl);
		PostMedia updatedPostMedia = postMediaRepository.save(postMedia);
		return new PostMediaResponseDto(updatedPostMedia);
	}

	// DB 삭제
	@Transactional
	public void deleteUrl(Long postId, Long mediaId) {
		PostMedia postMedia = findById(mediaId);
		postMediaRepository.delete(postMedia);
	}

	// mediaId로 postMedia 찾기
	public PostMedia findById(Long mediaId) {
		return postMediaRepository.findById(mediaId).orElseThrow(
				() -> new IllegalArgumentException("해당 미디어를 찾을 수 없습니다.")
		);
	}


}
