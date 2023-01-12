package com.green.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.green.domain.entity.AttendanceEntity;
import com.green.domain.entity.EmployeesEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceInsertDTO {
	
	
	private LocalDate date; //날짜
	private LocalDateTime inTime; //출근시간
	private LocalDateTime outTime; //퇴근시간
//	private String attendStatus;
	
	public AttendanceEntity attendanceEntity() {
		return AttendanceEntity.builder()
				.date(date)
				.inTime(inTime)
				.outTime(outTime)
				.build();
	}

	
	
	
//	public AttendanceInsertDTO(AttendanceEntity e) {
//		
//		this.id = e.getId();
//		this.employee_id= e.getEmployee().getId();
////		this.name = e.getEmployee().getName();
////		this.department = e.getEmployee().getDepartment().getName();
////		this.team = e.getEmployee().getTeam().getName();
////		this.position = e.getEmployee().getPosition().getPosition();
//		this.date = e.getDate();
//		this.inTime = e.getInTime();
//		this.outTime = e.getOutTime();
////		this.attendStatus = e.getAttendStatus().toString();
//	}
//	
}
