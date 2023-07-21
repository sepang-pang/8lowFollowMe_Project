package com.sparta.followfollowmeproject.admin.entity;

import com.sparta.followfollowmeproject.comment.entity.Comment;
import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "admin")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "admin")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "admin")
    private List<User> userList = new ArrayList<>();

}
