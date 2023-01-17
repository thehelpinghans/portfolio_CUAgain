package com.green.domain.dto;

import com.green.domain.entity.EmployeesEntity;
import com.green.domain.entity.StoreEntity;

import lombok.Data;

@Data
public class StoreRegDTO {
	
	private String name;
	
	private String content;
	
	
	public StoreEntity regist() {
		return StoreEntity.builder()
		.name(name)
		.content(content)
		.build();
	}
	
}
