package com.telemedicine.backend.entity;

import com.telemedicine.backend.entity.enums.VerificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
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
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @Column(name = "title")
    private String title;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "rating_average")
    private Double ratingAverage;

    @Column(name = "rating_count")
    private Integer ratingCount;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", nullable = false)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "doctor_identity_docs", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "document_url")
    private Set<String> identityDocuments;

    // THÊM DÒNG NÀY: Liên kết ngược lại với Freelance Profile (Nếu có)
    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DoctorFreelanceProfile freelanceProfile;
}