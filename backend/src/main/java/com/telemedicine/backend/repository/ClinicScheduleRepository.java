package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.ClinicSchedule;
import com.telemedicine.backend.entity.enums.ScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClinicScheduleRepository extends JpaRepository<ClinicSchedule, UUID> {

    // Lấy lịch trống của một Phòng khám cụ thể từ ngày hôm nay trở đi
    @Query("SELECT cs FROM ClinicSchedule cs " +
            "WHERE cs.clinic.id = :clinicId " +
            "AND cs.workingDate >= :currentDate " +
            "AND cs.status = :status " +
            "ORDER BY cs.workingDate ASC, cs.startTime ASC")
    List<ClinicSchedule> findAvailableSchedulesByClinic(
            @Param("clinicId") UUID clinicId,
            @Param("currentDate") LocalDate currentDate,
            @Param("status") ScheduleStatus status);
}