package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.ClinicService;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ClinicServiceRepository extends JpaRepository<ClinicService, UUID> {
}