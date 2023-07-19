package com.sparta.followfollowmeproject.multimedia.controller;

import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.multimedia.TestImageService;
import com.sparta.followfollowmeproject.multimedia.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class FileUploadController {

	private final S3UploadService s3UploadService;
	private final TestImageService testImageService;

	@PostMapping("/upload")
	public ResponseEntity<ApiResponseDto> uploadFile(@RequestParam("images") MultipartFile multipartFile) throws IOException {
		String imageUrl = s3UploadService.upload(multipartFile);

		// DB 저장 테스트
		testImageService.uploadFile(imageUrl);

		return ResponseEntity.ok().body(new ApiResponseDto(imageUrl, HttpStatus.OK.value()));
	}
}
