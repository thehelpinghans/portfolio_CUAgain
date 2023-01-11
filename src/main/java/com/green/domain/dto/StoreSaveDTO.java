package com.green.domain.dto;

import com.green.domain.entity.StoreEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreSaveDTO {
	
	private long id;
	
	private String name;
	
	private long managerId;
	
	private long addressId;

	public StoreSaveDTO(StoreEntity e) {
		this.id = e.getId();
		this.name = e.getName();
		this.managerId = e.getManager_id();
		this.addressId = e.getAddress_id();
	}
	
	
}
