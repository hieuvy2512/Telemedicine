package com.telemedicine.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Tên bác sĩ
    private String specialty; // Chuyên khoa (Tim mạch, Nhi khoa...)
    private String imageUrl; // Link ảnh avatar

    @Column(columnDefinition = "TEXT")
    private String description; // Chi tiết kinh nghiệm, học vấn...

    // Tự động tạo Constructor, Getter, Setter (Nếu bạn xài Lombok thì chỉ cần gắn
    // @Data ở trên cùng là khỏi cần gõ mấy cái này)
}