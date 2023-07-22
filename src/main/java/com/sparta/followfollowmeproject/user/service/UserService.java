package com.sparta.followfollowmeproject.user.service;

import com.sparta.followfollowmeproject.advice.custom.DuplicateException;
import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.common.jwt.JwtUtil;
import com.sparta.followfollowmeproject.user.dto.SignupRequestDto;
import com.sparta.followfollowmeproject.user.entity.User;
import com.sparta.followfollowmeproject.user.entity.UserRoleEnum;
import com.sparta.followfollowmeproject.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;


@Service
@Slf4j(topic = "UserService 로그")
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final JwtUtil jwtUtil;


    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public ResponseEntity<ApiResponseDto> signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new DuplicateException("중복되는 회원입니다.");
        }


        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new DuplicateException("중복되는 email 입니다");

        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("ADMIN TOKEN 값이 올바르지 않습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(username, password, email, role);
        userRepository.save(user);
        log.info("사용자 등록 확인");
        log.info("회원가입 성공");
        return ResponseEntity.ok().body(new ApiResponseDto("회원가입 성공", 200));
    }

    public ResponseEntity<ApiResponseDto> logOut(HttpServletRequest request) {
        String token = jwtUtil.getJwtFromHeader(request);
        if (StringUtils.hasText(token)) {
            jwtUtil.addToBlacklist(token);
        }
        return ResponseEntity.ok().body(new ApiResponseDto("로그아웃 성공", 200));
    }
}