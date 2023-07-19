package com.sparta.followfollowmeproject.multimedia;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestImageService {
	private final TestImageRepository testImageRepository;

	// 이미지 업로드
	@Transactional
	public void uploadFile(String imageUrl) {
		TestImage testImage = new TestImage(imageUrl);
		testImageRepository.save(testImage);
	}
}
