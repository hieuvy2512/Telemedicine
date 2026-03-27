package com.telemedicine.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // 1. Nếu là các API public (như đăng nhập, swagger) thì cho đi qua luôn, không
        // kiểm tra
        if (request.getServletPath().contains("/api/v1/auth") || request.getServletPath().contains("/swagger-ui")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. Lấy header "Authorization" từ request
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // 3. Nếu header trống hoặc không bắt đầu bằng chữ "Bearer ", tức là không có vé
        // -> Bỏ qua, để cho Spring Security mặc định chặn lại
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 4. Cắt lấy chuỗi token (bỏ chữ "Bearer " dài 7 ký tự)
        jwt = authHeader.substring(7);

        try {
            // 5. Giải mã token để lấy Email
            userEmail = jwtService.extractUsername(jwt);

            // 6. Nếu có Email và chưa được xác thực trong Context hiện tại
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Móc Database lên để lấy thông tin User (Qua UserDetailsService)
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                // 7. Nếu Token hợp lệ -> Cấp một cái thẻ vào cửa (AuthenticationToken)
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // 8. Đóng mộc "Đã xác thực" vào hệ thống cho request này
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            // Nếu token hết hạn hoặc bị sửa đổi giả mạo, catch lỗi và không làm gì cả
            // (Security sẽ tự chặn)
            System.out.println("Lỗi xác thực JWT: " + e.getMessage());
        }

        // 9. Mời đi tiếp vào Controller
        filterChain.doFilter(request, response);
    }
}