package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Để trống thế này là xài được cả đống hàm xịn rồi!
}