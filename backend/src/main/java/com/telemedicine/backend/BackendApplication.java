package com.telemedicine.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		// 1. ÉP MÚI GIỜ NGAY TẠI ĐÂY - TRƯỚC KHI SPRING BOOT KỊP LÀM BẤT CỨ ĐIỀU GÌ
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

		// 2. Sau đó mới cho Spring Boot chạy
		SpringApplication.run(BackendApplication.class, args);
	}
}