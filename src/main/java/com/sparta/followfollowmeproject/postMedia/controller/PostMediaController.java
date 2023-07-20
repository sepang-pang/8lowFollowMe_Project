package com.sparta.followfollowmeproject.postMedia.controller;

import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.postMedia.dto.PostMediaResponseDto;
import com.sparta.followfollowmeproject.postMedia.service.PostMediaService;
import com.sparta.followfollowmeproject.postMedia.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostMediaController {

	private final S3UploadService s3UploadService;
	private final PostMediaService postMediaService;

	// 선택한 게시글의 미디어 조회
	@GetMapping("/posts/{postId}/media")
	public ResponseEntity<List<PostMediaResponseDto>> getMediaByPostId(@PathVariable Long postId) {
		List<PostMediaResponseDto> responseDtos = postMediaService.getMediaByPostId(postId);
		return ResponseEntity.ok().body(responseDtos);
	}

	// 선택한 게시글에 미디어 저장
	@PostMapping("/posts/{postId}/media")
	public ResponseEntity<PostMediaResponseDto> uploadMedia(@PathVariable Long postId, @RequestParam("media") MultipartFile multipartFile) throws IOException {
		// AWS S3 저장
		String mediaUrl = s3UploadService.uploadFile(multipartFile);
		// DB 저장
		PostMediaResponseDto postMediaResponseDto = postMediaService.uploadUrl(postId, mediaUrl);
		return ResponseEntity.ok().body(postMediaResponseDto);
	}

	// 선택한 미디어 수정
	@PutMapping("/posts/{postId}/media/{mediaId}")
	public ResponseEntity<PostMediaResponseDto> updateMedia(@PathVariable Long postId, @PathVariable Long mediaId, @RequestParam("media") MultipartFile multipartFile) throws IOException {
		// AWS S3 삭제
		String deletedMediaUrl = postMediaService.findById(mediaId).getMdeiaUrl();
		s3UploadService.deleteFile(deletedMediaUrl);
		// AWS S3 저장
		String updatedMediaUrl = s3UploadService.uploadFile(multipartFile);
		// DB 수정
		PostMediaResponseDto postMediaResponseDto = postMediaService.updateUrl(postId, mediaId, updatedMediaUrl);
		return ResponseEntity.ok().body(postMediaResponseDto);
	}

	// 선택한 미디어 삭제
	@DeleteMapping("/posts/{postId}/media/{mediaId}")
	public ResponseEntity<ApiResponseDto> deleteMedia(@PathVariable Long postId, @PathVariable Long mediaId) throws IOException {
		// AWS S3 삭제
		String mediaUrl = postMediaService.findById(mediaId).getMdeiaUrl();
		s3UploadService.deleteFile(mediaUrl);
		// DB 삭제
		postMediaService.deleteUrl(postId, mediaId);
		return ResponseEntity.ok().body(new ApiResponseDto("미디어 삭제 성공", 200));
	}
}
