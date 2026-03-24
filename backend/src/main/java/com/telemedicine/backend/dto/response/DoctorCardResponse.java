package com.telemedicine.backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;
import java.util.List;

@Data
@Builder
public class DoctorCardResponse {
    private UUID id;
    private String lastName;
    private String firstName;
    private String avatarUrl;
    private String title; // Vd: BS. CK2, ThS. BS
    private List<String> specialties;// Vd: Nhi khoa
    // Tạm thời fix cứng bệnh viện nếu DB chưa có bảng Clinic/Hospital
    @Builder.Default
    private String workplace = "Bệnh viện Chợ Rẫy";
}