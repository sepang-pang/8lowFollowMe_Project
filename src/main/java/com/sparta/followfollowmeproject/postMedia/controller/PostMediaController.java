package com.sparta.followfollowmeproject.postMedia.controller;

import com.sparta.followfollowmeproject.postMedia.dto.PostMediaResponseDto;
import com.sparta.followfollowmeproject.postMedia.service.PostMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostMediaController {

	private final PostMediaService s3UploadService;
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
		String mediaUrl = s3UploadService.upload(multipartFile);

		// DB 저장
		PostMediaResponseDto postMediaResponseDto = postMediaService.uploadFile(postId, mediaUrl);

		return ResponseEntity.ok().body(postMediaResponseDto);
	}
}
