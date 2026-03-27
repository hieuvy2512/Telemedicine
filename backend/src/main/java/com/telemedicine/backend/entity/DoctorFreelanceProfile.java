package com.telemedicine.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "doctor_freelance_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorFreelanceProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Quan hệ 1-1 với bảng Doctor cốt lõi
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false, unique = true)
    private Doctor doctor;

    @Column(name = "consultation_fee", nullable = false)
    private BigDecimal consultationFee;

    @Column(name = "practice_address")
    private String practiceAddress;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "freelance_practice_images", joinColumns = @JoinColumn(name = "freelance_profile_id"))
    @Column(name = "image_url")
    private Set<String> practiceImages;

    @Builder.Default
    @Column(name = "is_payment_required")
    private Boolean isPaymentRequired = false;
}