package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, UUID> {
    // Lấy toàn bộ bệnh án của một hồ sơ bệnh nhân (để làm Sổ tay sức khỏe điện tử)
    List<MedicalRecord> findByPatientProfile_IdOrderByCreatedAtDesc(UUID patientProfileId);

    // Lấy bệnh án của một cuộc hẹn cụ thể
    Optional<MedicalRecord> findByAppointment_Id(UUID appointmentId);
}