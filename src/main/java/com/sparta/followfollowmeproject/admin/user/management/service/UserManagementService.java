package com.sparta.followfollowmeproject.admin.user.management.service;

import com.sparta.followfollowmeproject.admin.entity.UserBlackList;
import com.sparta.followfollowmeproject.admin.repository.UserBlackListRepository;
import com.sparta.followfollowmeproject.admin.user.management.dto.UserManagementRequestDto;
import com.sparta.followfollowmeproject.admin.user.management.dto.UserManagementResponseDto;
import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.user.entity.User;
import com.sparta.followfollowmeproject.user.entity.UserRoleEnum;
import com.sparta.followfollowmeproject.user.repository.UserRepository;
import com.sparta.followfollowmeproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "UserManagementService Log")
public class UserManagementService {

    private final UserRepository userRepository;
    private final UserBlackListRepository userBlackListRepository;
    private final UserService userService;

    // 전체 유저 목록 조회
    @Transactional(readOnly = true)
    public List<UserManagementResponseDto> getUserList() {
        log.info("유저 목록 조회");

        // 전체 유저 조회 후 반환
        List<User> users = userRepository.findAll();
        return users.stream().map(
                user -> new UserManagementResponseDto(user)
        ).toList();
    }

    // 회원 삭제하기
    @Transactional
    public ResponseEntity<ApiResponseDto> deleteUser(String username) {
        log.info("회원 삭제 시작");

        // 유저 조회
        User user = findUser(username);

        // 유저 삭제
        userRepository.delete(user);

        // 성공 메시지 반환
        return ResponseEntity.ok().body(new ApiResponseDto("유저 삭제 성공", 200));
    }

    // 회원 운영진으로 승격
    @Transactional
    public ResponseEntity<ApiResponseDto> promotionUserRole(String username, UserManagementRequestDto requestDto) {
        // 유저 조회
        User user = findUser(username);

        // 이미 관리자일 경우 예외 반환
        if(user.getRole().equals(UserRoleEnum.valueOf(requestDto.getRole()))) {
            throw new IllegalArgumentException("이미 관리자입니다.");
        }

        // 회원 Role 변경
        user.promotionUserRole(requestDto);
        userRepository.save(user);

        // 성공 메시지 반환
        return ResponseEntity.ok().body(new ApiResponseDto("유저 승격 성공", 200));
    }

    // 유저 차단
    @Transactional
    public ResponseEntity<ApiResponseDto> blockUser(String username) {
        // 유저 조회
        User user = findUser(username);

        // 블랙리스트
        if(user.isBlocked()) {
            throw new IllegalArgumentException("이미 차단된 유저입니다.");
        }

        UserBlackList userBlackList = new UserBlackList(user);
        user.switchBlock();

        // 블랙리스트 저장
        userBlackListRepository.save(userBlackList);

        return ResponseEntity.ok().body(new ApiResponseDto("유저를 블랙리스트에 등록했습니다.", 200));
    }

    // 유저 차단 해제
    @Transactional
    public ResponseEntity<ApiResponseDto> unblockUser(String username) {
        // 유저 조회
        User user = findUser(username);
        UserBlackList userBlackList = userBlackListRepository.findByUserId(user.getId())
                .orElseThrow(()-> new IllegalArgumentException("블랙리스트 유저가 아닙니다."));

        user.switchBlock();
        userBlackListRepository.delete(userBlackList);

        return ResponseEntity.ok().body(new ApiResponseDto("유저를 블랙리스트에서 해제했습니다.", 200));
    }


    // 유저 찾기 메서드
    public User findUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다"));
    }


}
