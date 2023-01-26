package com.green.domain.dto;

import java.util.List;

import com.green.domain.entity.DepartmentEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class DepartmentDTO {
	
	private long id;
	private String name;
//	private String managerName;
//	
	List<TeamDTO> teams;
	
	public DepartmentDTO(DepartmentEntity dep) {
		this.id = dep.getId();
		this.name = dep.getName();
	//	this.managerName = dep.getManager().getName();
	}
	public DepartmentDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
