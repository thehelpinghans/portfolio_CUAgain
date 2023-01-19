package com.green.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.green.domain.entity.AttendanceEntity;
import com.green.domain.entity.EmployeesEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceListDTO {
	
	private long id; //근태pk
	private long employeeId; //사원번호
	private LocalDate date; //날짜
	private Date inTime; //출근시간
	private Date outTime; //퇴근시간
	private String attendStatus; //상태
	private int totalPage; //총 페이지 수
	
	
	
	public AttendanceListDTO(AttendanceEntity e) {
		this.id = e.getId();
		this.employeeId = e.getEmployee().getId(); 
		this.date = e.getDate();
		this.inTime = e.getInTime();
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
