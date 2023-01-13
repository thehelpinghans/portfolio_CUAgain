package com.green.domain.dto;

import com.green.domain.entity.StoreEntity;

import lombok.Data;

@Data
public class StoreListDTO {
	
	
	private String name;
	
	private long managerId;
	
	private String jibunAddress;
	
	public StoreListDTO(StoreEntity e) {
		this.name = e.getName();
		this.managerId = e.getManager().getId();
		this.jibunAddress = e.getAddress().getJibunAddress();
	}
	
}
