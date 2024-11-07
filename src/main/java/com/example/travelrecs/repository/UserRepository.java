package com.example.travelrecs.repository;

import com.example.travelrecs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 根据用户名查找用户
    Optional<User> findByUserName(String userName);

    // 检查用户名是否存在
    boolean existsByUserName(String userName);
}
