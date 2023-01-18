package com.green.domain.dto;

import com.green.domain.entity.TeamEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TeamDTO {

	private long id;
	private String name;
	//상위부서번호
//	private DepartmentEntity depId;
	private long departId;
	//*
	public TeamDTO(TeamEntity team) {
		this.id = team.getId();
		this.name = team.getName();
		System.err.println(team.getDep());
//		this.depId = team.getDep();
		System.err.println(team.getDep().getId());
		this.departId = team.getDep().getId();
	}
	//*/


	
	
}