package com.telemedicine.backend.entity;

import com.telemedicine.backend.entity.enums.VerificationStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clinics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_user_id", nullable = false)
    private User adminUser;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "logo_url")
    private String logoUrl;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "clinic_cover_images", joinColumns = @JoinColumn(name = "clinic_id"))
    @Column(name = "image_url")
    private Set<String> coverImages;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status", nullable = false)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

    @Builder.Default
    @Column(name = "require_service_selection")
    private Boolean requireServiceSelection = false;

    @Builder.Default
    @Column(name = "require_specialty_selection")
    private Boolean requireSpecialtySelection = false;

    @Builder.Default
    @Column(name = "allow_doctor_selection")
    private Boolean allowDoctorSelection = true;

    @Builder.Default
    @Column(name = "require_prepayment")
    private Boolean requirePrepayment = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "clinic_doctors", joinColumns = @JoinColumn(name = "clinic_id"), inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private Set<Doctor> doctors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "clinic_specialties", joinColumns = @JoinColumn(name = "clinic_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}