package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, UUID> {
}