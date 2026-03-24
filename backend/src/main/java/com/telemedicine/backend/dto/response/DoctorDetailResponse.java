package com.telemedicine.backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

@Data
@Builder
public class DoctorDetailResponse {
    private UUID id;
    private String fullName;
    private String avatarUrl;
    private String title;
    private List<String> specialties;
    private String trainingProcess;
    private String experience;
    private Integer experienceYears;
    private String bio; // Dùng làm phần mô tả khám chữa bệnh
    private BigDecimal consultationFee;
    @Builder.Default
    private String workplace = "Bệnh viện Chợ Rẫy";
}