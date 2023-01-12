package com.green.domain.dto;

import com.green.domain.entity.StoreEntity;

import lombok.Data;

@Data
public class StoreSaveDTO {
	
	private long id;
	
	private String name;
	
	private String content;
	
	private String managerName;
	
	private String address;

	public StoreSaveDTO(StoreEntity e) {
		this.id = e.getId();
		this.name = e.getName();
		this.content = e.getContent();
		this.managerName = e.getManager().getName();
		this.address = e.getAddress().getDetailAddress();
	}
	
	
}
