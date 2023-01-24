package com.green.domain.dto;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDetailDTO {
    private Long id;
    private LocalDate date;
    private Date inTime;
    private Date outTime;
    private String attendStatus;
    private EmployeesDetailDTO employee;
}
