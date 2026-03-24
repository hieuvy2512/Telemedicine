package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.DoctorSchedule;
import com.telemedicine.backend.entity.enums.ScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, UUID> {

    /**
     * Lấy danh sách lịch khám CÒN TRỐNG của một bác sĩ từ ngày hiện tại trở đi.
     * Tự động sắp xếp tăng dần theo ngày và giờ bắt đầu.
     */
    @Query("SELECT ds FROM DoctorSchedule ds " +
            "WHERE ds.doctor.id = :doctorId " +
            "AND ds.workingDate >= :currentDate " +
            "AND ds.status = :status " +
            "ORDER BY ds.workingDate ASC, ds.startTime ASC")
    List<DoctorSchedule> findAvailableSchedules(
            @Param("doctorId") UUID doctorId,
            @Param("currentDate") LocalDate currentDate,
            @Param("status") ScheduleStatus status);
}