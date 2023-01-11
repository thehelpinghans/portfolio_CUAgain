package com.green.domain.dto;

import java.time.LocalDateTime;

import com.green.domain.entity.PaymentEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
	
	 private long id;

	 private String title;

	 private String content;
	 
	 private LocalDateTime createdDate;
	 
	 private LocalDateTime updatedDate;
	 
	 private long status;

	public PaymentDTO(PaymentEntity e) {
		this.id = e.getId();
		this.title = e.getTitle();
		this.content = e.getContent();
		this.createdDate = e.getCreatedDate();
		this.updatedDate = e.getUpdatedDate();
		this.status = e.getStatus();
	}
	 
	 
}