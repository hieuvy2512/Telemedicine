package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // Dùng để Đăng nhập bằng Email
    Optional<User> findByEmail(String email);

    // Dùng để Đăng nhập hoặc kiểm tra trùng lặp bằng Số điện thoại
    Optional<User> findByPhoneNumber(String phoneNumber);

    // Kiểm tra xem email hoặc số điện thoại đã tồn tại chưa (khi đăng ký)
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}