package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.DoctorFreelanceProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DoctorFreelanceProfileRepository extends JpaRepository<DoctorFreelanceProfile, UUID> {
}