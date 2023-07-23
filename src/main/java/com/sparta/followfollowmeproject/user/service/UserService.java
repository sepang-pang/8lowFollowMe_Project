package com.sparta.followfollowmeproject.user.service;

import com.sparta.followfollowmeproject.advice.custom.DuplicateException;
import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.common.jwt.JwtUtil;
import com.sparta.followfollowmeproject.user.dto.SignupRequestDto;
import com.sparta.followfollowmeproject.user.entity.User;
import com.sparta.followfollowmeproject.user.entity.UserRoleEnum;
import com.sparta.followfollowmeproject.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

/*    public ResponseEntity<ApiResponseDto> login(String email, String password) {
        // 1. 데이터베이스에서 email에 해당하는 사용자 찾기
        Optional<User> userOptional = userRepository.findByEmail(email);

        // 2. 사용자가 없거나 비밀번호가 일치하지 않는 경우, 로그인 실패
        if (userOptional.isEmpty()) {
            log.error(email + "에 해당하는 사용자를 찾을 수 없습니다.");
            return ResponseEntity.badRequest().body(new ApiResponseDto("로그인 실패: 사용자를 찾을 수 없습니다.", 400));
        }

        // 3. 세션 또는 JWT 생성 후 반환
        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.error(email + ": 비밀번호가 일치하지 않습니다.");
            return ResponseEntity.badRequest().body(new ApiResponseDto("로그인 실패: 비밀번호가 일치하지 않습니다.", 400));
        }
        String accessToken = jwtUtil.createToken(user.getUsername(), user.getRole().toString());
        log.info(email + "로그인 성공");
        return ResponseEntity.ok().body(new ApiResponseDto("로그인 성공", 200));
    }*/

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
}