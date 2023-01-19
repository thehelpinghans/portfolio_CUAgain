package com.green.domain.dto;

import java.util.Date;

import com.green.domain.entity.AttendanceEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminAttendanceListDTO {
	
	private long employeeId;
	private String name;
	private String department;
	private String position;
	private Date inTime; //출근시간
	private Date outTime; //퇴근시간
	private String attendStatus; //상태
	

	public AdminAttendanceListDTO(AttendanceEntity a) {
		this.inTime = a.getInTime();
		this.outTime = a.getOutTime();
		this.attendStatus = a.getAttendStatus();
		this.employeeId = a.getEmployee().getId();
		this.name = a.getEmployee().getName();
		this.department = a.getEmployee().getDep().getName();
		this.position = a.getEmployee().getPosition().getPosition();
	}


	

	

}
