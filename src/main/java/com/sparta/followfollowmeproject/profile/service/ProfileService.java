package com.sparta.followfollowmeproject.profile.service;

import com.sparta.followfollowmeproject.profile.dto.ProfileRequestDto;
import com.sparta.followfollowmeproject.profile.dto.ProfileResponseDto;
import com.sparta.followfollowmeproject.profile.entity.Profile;
import com.sparta.followfollowmeproject.profile.repository.ProfileRepository;
import com.sparta.followfollowmeproject.user.entity.User;
import com.sparta.followfollowmeproject.user.entity.UserRoleEnum;
import com.sparta.followfollowmeproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j(topic = "ProfileService 관련 로그")
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    // 프로필 작성
    @Transactional
    public ProfileResponseDto createProfile(ProfileRequestDto profileRequestDto, User user) {
        // 이미 프로필을 작성했으면 예외 발생
        if (profileRepository.findById(user.getId()).isPresent())
            throw new IllegalArgumentException("이미 프로필을 등록하였습니다.");

        // 프로필에 dto 내용 반영
        Profile profile = new Profile(profileRequestDto, user);

        // 리포지토리 저장
        profileRepository.save(profile);

        // 반환
        return new ProfileResponseDto(profile);

    }

    // 프로필 조회
    @Transactional(readOnly = true)
    public List<ProfileResponseDto> getProfile(String username) {
        // 해당 유저가 존재하는지 확인
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));

        // 프로필 조회
        return profileRepository.findById(user.getId()).stream().map(ProfileResponseDto::new).toList();

    }

    // 프로필 수정
    @Transactional
    public ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto, User user) {
        // 해당 프로필이 존재하는지 확인
        Profile profile = findProfile(user);

        // 프로필 수정 권한 확인 -> 사실 지금 의미 없어 보이는 코드, 추후 관리자 기능 따로 만들어야겠다.
        if (!profile.getUsername().equals(user.getUsername()) && user.getRole().equals(UserRoleEnum.ADMIN))
            throw new IllegalArgumentException("프로필 수정 권한이 없습니다");

        // 프로필 수정
        profile.update(profileRequestDto);

        // DB 저장
        profileRepository.save(profile);

        // 반환
        return new ProfileResponseDto(profile);

    }

    // ------ 공통 메서드 ------ //
    public Profile findProfile(User user) {
        return profileRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 프로필은 존재하지 않습니다"));
    }
}
