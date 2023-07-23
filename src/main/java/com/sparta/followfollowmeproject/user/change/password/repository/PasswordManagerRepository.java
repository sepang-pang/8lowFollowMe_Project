package com.sparta.followfollowmeproject.user.change.password.repository;

import com.sparta.followfollowmeproject.user.change.password.entity.PasswordManager;
import com.sparta.followfollowmeproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordManagerRepository extends JpaRepository<PasswordManager, Long> {
    List<PasswordManager> findTop3ByUserOrderByCreatedAtDesc(User user);
}
