package com.telemedicine.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

// 1. Đảm bảo các annotation nằm sát nhau và ngay trên đầu class
@Entity
@Table(name = "doctor_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "working_date", nullable = false)
    private LocalDate workingDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    // Đổi tên thành ScheduleStatus để không bị trùng (Duplicate)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ScheduleStatus status = ScheduleStatus.AVAILABLE;

    @Column(name = "slot_duration")
    @Builder.Default
    private Integer slotDuration = 30;
}

// 2. Để Enum ở dưới cùng, bên ngoài dấu ngoặc nhọn của class trên
enum ScheduleStatus {
    AVAILABLE,
    BOOKED,
    PAUSED
}