package com.green.domain.dto;

import com.green.domain.entity.StoreEntity;

import lombok.Data;

@Data
public class StoreListDTO {
	
	private long id;
	
	private String name;
	
	private String managerId;
	
	private String jibunAddress;
	
	public StoreListDTO(StoreEntity e) {
		this.id = e.getId();
		this.name = e.getName();
		this.managerId = e.getManager().getName();
		this.jibunAddress = e.getAddress().getJibunAddress();
	}
	
}
