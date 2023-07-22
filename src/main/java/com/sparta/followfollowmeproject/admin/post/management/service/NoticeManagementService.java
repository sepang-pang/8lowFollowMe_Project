package com.sparta.followfollowmeproject.admin.post.management.service;

import com.sparta.followfollowmeproject.admin.post.management.dto.NoticeManagementResponseDto;
import com.sparta.followfollowmeproject.post.dto.PostRequestDto;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.post.repository.PostRepository;
import com.sparta.followfollowmeproject.user.entity.User;
import com.sparta.followfollowmeproject.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeManagementService {

    private final PostRepository postRepository;

    public NoticeManagementResponseDto createNotice(PostRequestDto requestDto, User admin) {
        Post post = new Post(requestDto, admin);
        post.markAsNotice(true); // 공지사항으로 설정
        post.switchPin(true); // 상단에 고정 여부 설정
        Post savedPost = postRepository.save(post);
        return new NoticeManagementResponseDto(savedPost);
    }

    public List<NoticeManagementResponseDto> getAllNotices(User admin) {
        List<Post> notices = postRepository.findByIsNoticeTrue();
        return notices.stream()
                .map(NoticeManagementResponseDto::new)
                .collect(Collectors.toList());
    }

    public NoticeManagementResponseDto getNotice(Long noticeId, User admin) {
        Post findNotice = findNotice(noticeId);
        return new NoticeManagementResponseDto(findNotice);
    }

    public NoticeManagementResponseDto updateNotice(Long noticeId, PostRequestDto requestDto, User admin) {
        Post findNotice = findNotice(noticeId);
        findNotice.update(requestDto);
        return new NoticeManagementResponseDto(findNotice);
    }

    public void deleteNotice(Long noticeId, User admin) {
        Post findNotice = findNotice(noticeId);
        postRepository.delete(findNotice);
    }

    private Post findNotice(Long noticeId) {
        return postRepository.findByIdAndIsNoticeTrue(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지는 존재하지 않습니다."));
    }
}

