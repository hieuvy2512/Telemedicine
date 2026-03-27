package com.telemedicine.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    // Best Practice cho Spring Security: Đặt tên biến là 'password' để tự động
    // match với phương thức getPassword() của interface UserDetails.
    // Dưới database vẫn lưu đúng tên cột là 'password_hash'.
    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Builder.Default
    @Column(name = "is_active")
    private Boolean isActive = true;

    @Builder.Default
    @Column(name = "is_verified_phone")
    private Boolean isVerifiedPhone = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // =========================================================================
    // CÁC HÀM BẮT BUỘC CỦA SPRING SECURITY (USERDETAILS)
    // =========================================================================

    // 1. Dịch quyền (Role) của User sang ngôn ngữ Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Trả về List chứa 1 quyền duy nhất. Ví dụ: ROLE_PATIENT
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    // 2. Dùng Email làm định danh (Username) để đăng nhập
    @Override
    public String getUsername() {
        return email;
    }

    // 3. Tài khoản có bị hết hạn không? -> Trả về true (không hết hạn)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 4. Tài khoản có bị khóa không? -> Trả về true (không khóa)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 5. Mật khẩu có bị hết hạn không? -> Trả về true (không hết hạn)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 6. Tài khoản có đang kích hoạt không? -> Lấy cột isActive trong DB của bạn
    @Override
    public boolean isEnabled() {
        return isActive;
    }
}