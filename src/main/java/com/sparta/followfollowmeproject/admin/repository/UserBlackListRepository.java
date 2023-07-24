package com.sparta.followfollowmeproject.admin.repository;

import com.sparta.followfollowmeproject.admin.entity.UserBlackList;
import com.sparta.followfollowmeproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBlackListRepository extends JpaRepository<UserBlackList, Long> {
    Optional<UserBlackList> findByUserId(Long id);
    Optional<UserBlackList> findByUsername(String username);
}