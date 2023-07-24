package com.sparta.followfollowmeproject.admin.post.management.controller;

import com.sparta.followfollowmeproject.admin.post.management.dto.NoticeManagementResponseDto;
import com.sparta.followfollowmeproject.admin.post.management.service.NoticeManagementService;
import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.common.security.UserDetailsImpl;
import com.sparta.followfollowmeproject.post.dto.PostRequestDto;
import com.sparta.followfollowmeproject.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/notice")
@RequiredArgsConstructor
@Secured(UserRoleEnum.Authority.ADMIN)
public class NoticeManagementController {

    private final NoticeManagementService noticeManagementService;

    /* 공지 작성 */
    @PostMapping
    public ResponseEntity<NoticeManagementResponseDto> createNotice(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        NoticeManagementResponseDto result = noticeManagementService.createNotice(requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(result);
    }

    /* 전체 공지 조회 */
    @GetMapping
    public ResponseEntity<List<NoticeManagementResponseDto>> getNotices(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<NoticeManagementResponseDto> results = noticeManagementService.getAllNotices(userDetails.getUser());
        return ResponseEntity.ok().body(results);
    }

    /* 공지 1개 조회 */
    @GetMapping("/{notice_id}")
    public ResponseEntity<NoticeManagementResponseDto> getNotice(@PathVariable Long noticeId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        NoticeManagementResponseDto result = noticeManagementService.getNotice(noticeId, userDetails.getUser());
        return ResponseEntity.ok().body(result);
    }

    /* 공지 수정 */
    @PutMapping("/{notice_id}")
    public ResponseEntity<NoticeManagementResponseDto> updateNotice(@PathVariable Long noticeId, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        NoticeManagementResponseDto result = noticeManagementService.updateNotice(noticeId, requestDto, userDetails.getUser());
        return ResponseEntity.ok().body(result);
    }

    /* 공지 삭제 */
    @DeleteMapping("/{notice_id}")
    public ResponseEntity<ApiResponseDto> deleteNotice(@PathVariable Long noticeId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        noticeManagementService.deleteNotice(noticeId, userDetails.getUser());
        return ResponseEntity.ok().body(
                new ApiResponseDto("삭제가 완료되었습니다.", HttpStatus.OK.value())
        );
    }
}