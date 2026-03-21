package com.telemedicine.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_profile_id")
    private PatientProfile patientProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", unique = true)
    private DoctorSchedule schedule;

    @Builder.Default
    private Status appointmentStatus = Status.PENDING; // PENDING, CONFIRMED, CANCELLED, COMPLETED

    @Column(name = "patient_notes", columnDefinition = "TEXT")
    private String patientNotes;

    @Column(name = "video_call_room_id")
    private String videoCallRoomId;

    @Column(name = "video_call_url")
    private String videoCallUrl;

    @Column(name = "actual_start_time")
    private LocalDateTime actualStartTime;

    @Column(name = "actual_end_time")
    private LocalDateTime actualEndTime;

    @Column(name = "payment_transaction_id")
    private String paymentTransactionId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

enum Status {
    PENDING,
    CONFIRMED,
    CANCELLED,
    COMPLETED
}