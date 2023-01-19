package com.green.domain.dto;

import org.hibernate.annotations.DynamicUpdate;

import com.green.domain.entity.EmployeesEntity;
import com.green.domain.entity.StoreEntity;

import lombok.Data;

@Data
public class StoreSaveDTO {
	
	private long id;
	
	private String name;
	
	private String content;
	
	private long managerId;
	
	private String postcode;
	
	private String roadAddress;
	
	private String jibunAddress;
	
	private String detailAddress;
	
	private String extraAddress;
	
	private String managerName;

	public StoreSaveDTO() {
		
	}
	
	public StoreSaveDTO(StoreEntity e) {
		this.id = e.getId();
		this.name = e.getName();
		this.content = e.getContent();
		this.managerId = e.getManager().getId();
		this.postcode = e.getAddress().getPostcode();
		this.roadAddress = e.getAddress().getRoadAddress();
		this.jibunAddress = e.getAddress().getJibunAddress();
		this.detailAddress = e.getAddress().getDetailAddress();
		this.extraAddress = e.getAddress().getExtraAddress();
		this.managerName = e.getManager().getName();
	}
	
	
}
