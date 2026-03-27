package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.Clinic;
import com.telemedicine.backend.entity.enums.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, UUID> {

    // Lấy danh sách phòng khám đã duyệt theo Tỉnh/Thành phố
    List<Clinic> findByCityAndVerificationStatus(String city, VerificationStatus status);
}