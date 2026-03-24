package com.telemedicine.backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Data
@Builder
public class DoctorScheduleResponse {
    private LocalDate date;
    private String dateDisplay; // Ví dụ: "Thứ Hai - 21/05"
    private List<ScheduleDto> timeSlots;

    /**
     * Helper để format ngày sang tiếng Việt cho Frontend dễ dùng
     */
    public static String formatVietnameseDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE - dd/MM", new Locale("vi", "VN"));
        String formatted = date.format(formatter);
        // Viết hoa chữ cái đầu cho đẹp
        return formatted.substring(0, 1).toUpperCase() + formatted.substring(1);
    }
}