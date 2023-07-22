package com.sparta.followfollowmeproject.admin.post.management.controller;

import com.sparta.followfollowmeproject.admin.post.management.service.PostManagementService;
import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.common.security.UserDetailsImpl;
import com.sparta.followfollowmeproject.post.dto.PostRequestDto;
import com.sparta.followfollowmeproject.post.dto.PostResponseDto;
import com.sparta.followfollowmeproject.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/posts")
@RequiredArgsConstructor
@Secured(UserRoleEnum.Authority.ADMIN)
public class PostManagementController {
    private final PostManagementService postManagementService;

    // 전체 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<PostResponseDto> results = postManagementService.getAllPosts();
        return ResponseEntity.ok().body(results);
    }

    // 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto result = postManagementService.getPostById(postId);
        return ResponseEntity.ok().body(result);
    }

    // 게시글 수정
    @PutMapping("/{post_id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto result = postManagementService.updatePost(postId, requestDto);
        return ResponseEntity.ok().body(result);
    }

    // 게시글 삭제
    @DeleteMapping("/{post_id}")
    public ResponseEntity<ApiResponseDto> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postManagementService.deletePost(postId);
        return ResponseEntity.ok().body(
                new ApiResponseDto("삭제가 완료되었습니다.", HttpStatus.OK.value())
        );
    }

}