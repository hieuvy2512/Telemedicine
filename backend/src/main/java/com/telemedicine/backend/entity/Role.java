package com.telemedicine.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name; // Vd: ROLE_PATIENT, ROLE_DOCTOR, ROLE_CLINIC_ADMIN, ROLE_ADMIN

    private String description;
}