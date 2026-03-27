package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.Doctor;
import com.telemedicine.backend.entity.enums.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    // Tìm danh sách bác sĩ ĐÃ DUYỆT theo Chuyên khoa (slug dùng cho URL Web:
    // /chuyen-khoa/tim-mach)
    @Query("SELECT d FROM Doctor d WHERE d.specialty.slug = :specialtySlug AND d.verificationStatus = 'APPROVED'")
    List<Doctor> findApprovedDoctorsBySpecialty(@Param("specialtySlug") String specialtySlug);

    // Thanh tìm kiếm: Tìm bác sĩ theo tên (Gõ 'Nguyễn' ra tất cả bác sĩ tên Nguyễn)
    @Query("SELECT d FROM Doctor d WHERE LOWER(d.user.firstName || ' ' || d.user.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) AND d.verificationStatus = 'APPROVED'")
    List<Doctor> searchDoctorsByName(@Param("keyword") String keyword);
}