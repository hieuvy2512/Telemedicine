package com.telemedicine.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "consultation_fee", nullable = false, precision = 12, scale = 2)
    private BigDecimal consultationFee;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Builder.Default
    @Column(name = "rating_average", precision = 3, scale = 2)
    private BigDecimal ratingAverage = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "rating_count")
    private Integer ratingCount = 0;

    @Column(name = "certificate_url")
    private String certificateUrl;

    @Builder.Default
    @Column(name = "is_verified_doctor")
    private Boolean isVerifiedDoctor = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}