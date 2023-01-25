package com.green.domain.dto;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;
@Data
public class AttendanceCalendarDTO {
    @DateTimeFormat(pattern = "yyyy-MM", iso = ISO.DATE)
    private Date month;
    private Long departmentId;
}
