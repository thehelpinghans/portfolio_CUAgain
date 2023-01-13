package com.green.domain.dto;

import java.time.LocalDateTime;

import com.green.domain.entity.DocumentEntity;

import lombok.Data;

@Data
public class PaymentDTO {
	
	 private long id;

	 private String title;

	 private String content;
	 
	 private LocalDateTime createdDate;
	 
	 private LocalDateTime updatedDate;
	 
	 private long status;
	 
	 private String employeesName;
	 
	 private String empName;

	public PaymentDTO(DocumentEntity e) {
		this.id = e.getId();
		this.title = e.getTitle();
		this.content = e.getContent();
		this.createdDate = e.getCreatedDate();
		this.updatedDate = e.getUpdatedDate();
		this.status = e.getStatus();
		this.employeesName = e.getEmployees_id().getName();
		this.empName = e.getEmployees_id().getName();
	}
	 
	 
}