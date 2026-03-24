package com.telemedicine.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Đánh dấu đây là file cấu hình của Spring Boot
@EnableWebSecurity // Kích hoạt tính năng Bảo mật web
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Tắt CSRF (Cross-Site Request Forgery) vì chúng ta dùng REST API và Token
                // (JWT) chứ không dùng Cookie/Session
                .csrf(csrf -> csrf.disable())

                // 2. Cấu hình quản lý Session là STATELESS (Không lưu trạng thái đăng nhập trên
                // server)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Phân quyền các đường dẫn (URL)
                .authorizeHttpRequests(auth -> auth
                        // Những API công khai, ai cũng được gọi (VD: Đăng ký, Đăng nhập, Xem danh sách
                        // bác sĩ, xem Swagger Docs)
                        .requestMatchers("/api/v1/public/**", "/api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**")
                        .permitAll()

                        // Những API dành riêng cho Bệnh nhân (Yêu cầu user có quyền PATIENT)
                        .requestMatchers("/api/v1/patient/**").hasAuthority("PATIENT")

                        // Những API dành riêng cho Bác sĩ (Yêu cầu user có quyền DOCTOR)
                        .requestMatchers("/api/v1/doctor/**").hasAuthority("DOCTOR")

                        // Những API dành riêng cho Quản trị viên (Yêu cầu user có quyền ADMIN)
                        .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")

                        // Tất cả các request khác chưa được liệt kê ở trên đều bắt buộc phải đăng nhập
                        // (có token hợp lệ)
                        .anyRequest().authenticated());

        // Lưu ý: Sau này khi bạn làm tính năng Đăng nhập bằng JWT (JSON Web Token),
        // bạn sẽ cần add thêm một dòng code ở đây để kiểm tra Token trước khi cho user
        // đi tiếp.

        return http.build();
    }

    // Bean này dùng để mã hóa mật khẩu.
    // Thay vì lưu "123456" vào database, nó sẽ băm ra thành chuỗi "$2a$10$..." cực
    // kỳ an toàn.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}