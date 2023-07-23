package com.sparta.followfollowmeproject.profile.entity;

import com.sparta.followfollowmeproject.common.entity.Timestamped;
import com.sparta.followfollowmeproject.profile.dto.ProfileRequestDto;
import com.sparta.followfollowmeproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "profile")
@NoArgsConstructor
public class Profile extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "intro")
    private String intro;

    @Column(name = "age")
    private int age;

    @Column(name = "photo")
    private String photoUrl;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    public Profile(ProfileRequestDto profileRequestDto, User user) {
        this.user = user;
        this.username = user.getUsername();
        this.nickName = profileRequestDto.getNickName();
        this.intro = profileRequestDto.getIntro();
        this.age = profileRequestDto.getAge();
        this.photoUrl = profileRequestDto.getMediaUrl();
    }

    public void update(ProfileRequestDto profileRequestDto) {

        this.nickName = profileRequestDto.getNickName();
        this.intro = profileRequestDto.getIntro();
        this.age = profileRequestDto.getAge();
        this.photoUrl = profileRequestDto.getMediaUrl();

    }
}
