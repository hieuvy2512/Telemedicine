package com.telemedicine.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "doctor_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DoctorSchedule extends BaseSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Trỏ vào hồ sơ khám tự do (Như Giải pháp 2 ở bước trước)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freelance_profile_id", nullable = false)
    private DoctorFreelanceProfile freelanceProfile;
}