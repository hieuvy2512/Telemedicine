package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, UUID> {
}