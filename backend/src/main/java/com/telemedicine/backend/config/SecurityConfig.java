package com.telemedicine.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.telemedicine.backend.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
// Bật tính năng phân quyền ngay trên từng hàm trong Controller bằng
// @PreAuthorize
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Inject Filter kiểm tra Token do chúng ta tự viết (sẽ làm ở bước sau)
    private final JwtAuthenticationFilter jwtAuthFilter;

    // Inject Provider chứa logic xác thực (kiểm tra email, so sánh password)
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Cấu hình CORS tích hợp sâu vào Security
                .cors(cors -> cors.configure(http))

                // 2. Tắt CSRF vì sử dụng JWT (Bắt buộc)
                .csrf(AbstractHttpConfigurer::disable)

                // 3. Quản lý Session là STATELESS (Mỗi request đều là một request mới, server
                // không nhớ ai cả)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 4. Khai báo AuthenticationProvider
                .authenticationProvider(authenticationProvider)

                // 5. CHÚ Ý: Chèn bộ lọc JWT của chúng ta vào TRƯỚC bộ lọc đăng nhập mặc định
                // của Spring
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // 6. Phân quyền URL (Routing Security)
                .authorizeHttpRequests(auth -> auth
                        // Public APIs (Ai cũng vào được)
                        .requestMatchers(
                                "/api/v1/auth/**", // Đăng nhập, Đăng ký, Refresh Token
                                "/api/v1/public/**", // Xem danh sách Bác sĩ, Phòng khám
                                "/v3/api-docs/**", // Dữ liệu cho Swagger
                                "/swagger-ui/**", // Giao diện Swagger
                                "/swagger-ui.html")
                        .permitAll()

                        // Private APIs - Dựa vào cột 'name' trong bảng Role (VD: ROLE_PATIENT,
                        // ROLE_DOCTOR)
                        // Spring Security dùng hàm hasRole("X") sẽ tự động gắn thêm tiền tố "ROLE_"
                        // thành "ROLE_X" để so sánh với Database.
                        .requestMatchers("/api/v1/patient/**").hasRole("PATIENT")
                        .requestMatchers("/api/v1/doctor/**").hasRole("DOCTOR")
                        .requestMatchers("/api/v1/clinic/**").hasAnyRole("CLINIC_ADMIN", "ADMIN")
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")

                        // Mọi request khác đều phải có Token hợp lệ
                        .anyRequest().authenticated());

        return http.build();
    }
}