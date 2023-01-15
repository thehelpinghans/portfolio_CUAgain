package com.green.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.green.domain.entity.AttendanceEntity;
import com.green.domain.entity.EmployeesEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceInsertDTO {
	
	private long id; //근태pk
	private EmployeesEntity employee; 
	private LocalDate date; //날짜
	private Date inTime; //출근시간
	private Date outTime; //퇴근시간
	private String attendStatus; //상태

	
	public AttendanceEntity toEntity() {
		return AttendanceEntity.builder()
				.date(date)
				.employee(employee)
				.inTime(inTime)
				.outTime(outTime)
				.attendStatus(attendStatus)
				.build();
	}


	public AttendanceInsertDTO(AttendanceEntity e) {
		this.outTime = e.getOutTime();
		this.attendStatus = e.getAttendStatus();
	}
	
//	public AttendanceEntity updateOutTime(Date outTime) {
//		return AttendanceEntity.builder()
//				.outTime(outTime)
//				.build();
//	}
	
	


//	public AttendanceInsertDTO(AttendanceEntity e) {
//		this.date = e.getDate();
//		this.inTime = e.getInTime();
//		this.outTime = e.getOutTime();
//		this.attendStatus = e.getAttendStatus();
//	}
//		

}
