package com.green.domain.dto;

import com.green.domain.entity.DepartmentEntity;

import lombok.Getter;

@Getter
public class DepartmentDTO {
	private long id;
	private String name;
//	private String managerName;
//	
	
	
	public DepartmentDTO(DepartmentEntity dep) {
		this.id = dep.getId();
		this.name = dep.getName();
	//	this.managerName = dep.getManager().getName();
	}
	
	
	
	
}
