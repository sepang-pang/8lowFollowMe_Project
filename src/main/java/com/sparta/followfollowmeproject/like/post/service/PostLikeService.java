package com.sparta.followfollowmeproject.like.post.service;

import com.sparta.followfollowmeproject.post.entity.Post;
import com.sparta.followfollowmeproject.user.entity.User;

public interface PostLikeService {
    void likePost(Long id, User user);
    void deleteLikePost(Long id, User user);
    boolean isOwnPost(Long id, User user);
    Post findPost(long id);

}