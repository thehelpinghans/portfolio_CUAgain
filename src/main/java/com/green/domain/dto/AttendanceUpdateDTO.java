package com.green.domain.dto;

import java.time.LocalDateTime;

import com.green.domain.entity.AttendanceEntity;

import lombok.Data;

@Data
public class AttendanceUpdateDTO {
	

	private LocalDateTime outTime; //퇴근시간
	//private String attendStatus;
	
	public AttendanceEntity attendanceEntity() {
		return AttendanceEntity.builder()
				.outTime(outTime)
				.build();
	}

	
}
