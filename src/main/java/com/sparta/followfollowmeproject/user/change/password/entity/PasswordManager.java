package com.sparta.followfollowmeproject.user.change.password.entity;

import com.sparta.followfollowmeproject.common.entity.Timestamped;
import com.sparta.followfollowmeproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "password_manager")
public class PasswordManager extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public PasswordManager(String password, User user) {
        this.password = password;
        this.user = user;
    }
}
