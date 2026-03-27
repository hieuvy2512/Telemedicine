package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.Appointment;
import com.telemedicine.backend.entity.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    // 1. DÀNH CHO BỆNH NHÂN: Xem lịch sử khám của mình (Sắp xếp mới nhất lên đầu)
    List<Appointment> findByPatientProfile_IdOrderByCreatedAtDesc(UUID patientProfileId);

    // 2. DÀNH CHO BÁC SĨ: Xem danh sách các cuộc hẹn theo Trạng thái (VD: đang chờ
    // khám)
    List<Appointment> findByDoctor_IdAndStatus(UUID doctorId, AppointmentStatus status);

    // 3. DÀNH CHO PHÒNG KHÁM: Quản lý cuộc hẹn của phòng khám
    List<Appointment> findByClinic_IdOrderByCreatedAtDesc(UUID clinicId);
}