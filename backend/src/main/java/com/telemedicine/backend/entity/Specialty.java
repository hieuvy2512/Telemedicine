package com.telemedicine.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "specialties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(unique = true)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "icon_url")
    private String iconUrl;
}