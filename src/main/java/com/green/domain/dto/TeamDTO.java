package com.green.domain.dto;

import com.green.domain.entity.DepartmentEntity;
import com.green.domain.entity.TeamEntity;

import lombok.Getter;

@Getter
public class TeamDTO {

	private long id;
	private String name;
	//상위부서번호
	private DepartmentEntity depId;
	
	
	public TeamDTO(TeamEntity team) {
		this.id = team.getId();
		this.name = team.getName();
		this.depId = team.getDep();
		
	}


	
	
}