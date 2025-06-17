package com.example.order.dao;

import com.example.order.pojo.UserPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserPojo, Long> {
    Optional<UserPojo> findByEmailId(String emailId);
    Optional<UserPojo> findByUserId(String userId);
} 