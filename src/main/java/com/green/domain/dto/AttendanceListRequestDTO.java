package com.green.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import com.green.domain.entity.AttendanceEntity;
import com.green.domain.entity.EmployeesEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class AttendanceListRequestDTO {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate start;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate end;
	
	

	

}
