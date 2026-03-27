package com.telemedicine.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Telemedicine API Documentation", description = "Tài liệu API cho đồ án Y tế từ xa Decoupled", version = "1.0", contact = @Contact(name = "Lý Hiểu Vy", email = "lyhieuvy2512@gmail.com")),
        // THÊM: Cấu hình Server (để test Local hoặc Server thật)
        servers = {
                @Server(url = "http://localhost:8080", description = "Local Environment"),
                @Server(url = "https://telemedicine-backend.onrender.com", description = "Production Environment")
        },
        // THÊM: Áp dụng cái ổ khóa "bearerAuth" cho toàn bộ hệ thống
        security = @SecurityRequirement(name = "bearerAuth"))
@SecurityScheme(name = "bearerAuth", description = "JWT authentication", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {
}