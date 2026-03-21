package com.telemedicine.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		// 1. CHUẨN QUỐC TẾ: Ép toàn bộ Server chạy giờ UTC NGAY LẬP TỨC
		// Đặt ở đây đảm bảo HikariCP và Hibernate khi khởi tạo sẽ dùng UTC,
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		// 2. Sau đó mới cho Spring Boot chạy
		SpringApplication.run(BackendApplication.class, args);

		System.out.println("Spring Boot started successfully with timezone: " + TimeZone.getDefault().getID());
	}
}