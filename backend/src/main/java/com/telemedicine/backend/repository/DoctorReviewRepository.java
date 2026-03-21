package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.DoctorReview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DoctorReviewRepository extends JpaRepository<DoctorReview, UUID> {
}