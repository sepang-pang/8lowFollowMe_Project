package com.sparta.followfollowmeproject.post.entity;

import com.sparta.followfollowmeproject.comment.entity.Comment;
import com.sparta.followfollowmeproject.common.entity.Timestamped;
import com.sparta.followfollowmeproject.like.post.entity.PostLike;
import com.sparta.followfollowmeproject.post.dto.PostRequestDto;
import com.sparta.followfollowmeproject.postMedia.entity.PostMedia;
import com.sparta.followfollowmeproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "is_notice", nullable = false) // 공지사항 여부를 나타내는 속성
    private boolean isNotice;                    // true면 공지사항, false면 일반 게시글

    @Column(name = "is_pinned", nullable = false) // 공지글 상단 고정 여부를 나타내는 속성
    private boolean isPinned; // true면 상단 고정, false면 일반 게시글

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)  // cascade( 영속성 전이 ) : 연관 관계 같이 삭제
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostMedia> postMediaList;


    public Post(PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.isNotice = false;  // 일반 게시글로 초기화
        this.isPinned = false; // 상단 고정 안 함으로 초기화
        this.user  = user;
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }


    public void setNotice(boolean notice) {
        this.isNotice = notice;
    }

    public void setIsPinned(boolean isPinned) {
        this.isPinned = isPinned;
    }

    public boolean getIsPinned() {
        return isPinned;
    }
}

