package com.sparta.followfollowmeproject.like.post.entity;

import com.sparta.followfollowmeproject.common.entity.Timestamped;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post_like")
public class PostLike extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 좋아요 수
    @Column(name = "like_count", nullable = false)
    private int likeCount = 0;


    public PostLike(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    // likeCount를 증가시키는 메서드
    public void increaseLikeCount() {
        this.likeCount++;
    }

    // likeCount를 감소시키는 메서드
    public void decreaseLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }
}
