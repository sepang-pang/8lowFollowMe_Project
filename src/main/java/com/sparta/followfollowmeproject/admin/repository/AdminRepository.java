package com.sparta.followfollowmeproject.admin.repository;

import com.sparta.followfollowmeproject.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}