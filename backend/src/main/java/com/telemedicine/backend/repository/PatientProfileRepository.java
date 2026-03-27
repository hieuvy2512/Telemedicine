package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientProfileRepository extends JpaRepository<PatientProfile, UUID> {
    // Trả về danh sách hồ sơ bệnh nhân của 1 tài khoản
    // (Vì 1 tài khoản User có thể tạo hồ sơ cho mình, cho con cái, cho bố mẹ...)
    List<PatientProfile> findByUser_Id(UUID userId);
}