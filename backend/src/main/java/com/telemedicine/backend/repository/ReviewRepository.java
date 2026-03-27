package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    // Lấy danh sách đánh giá của 1 Bác sĩ
    List<Review> findByDoctor_IdOrderByCreatedAtDesc(UUID doctorId);

    // Lấy danh sách đánh giá của 1 Phòng khám
    List<Review> findByClinic_IdOrderByCreatedAtDesc(UUID clinicId);
}