package com.green.domain.dto;

import java.time.LocalDateTime;

import com.green.domain.entity.DocumentEntity;

import lombok.Data;

@Data
public class DocumentInsertDTO {
	
	 private String title;

	 private String content;
	 
	 private long writerId;
	 
	 private long acceptorId;
}