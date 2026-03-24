package com.telemedicine.backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import com.telemedicine.backend.entity.enums.ScheduleStatus;

@Data
@Builder
public class ScheduleDto {
    private UUID scheduleId;
    private LocalDate workingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private ScheduleStatus status;
}