package com.green.domain.entity;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "department")
@Entity
public class DepartmentEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;   //부서번호
	private String name; //부서이름
	

	public DepartmentEntity updateDepartmentName(String departmentName) {
		name=departmentName;
		return this;
	}
	
	@OneToOne //담당자
	@JoinColumn
	private EmployeesEntity manager;
	/*
				//테이블이 중복으로 생기지않기위해 (mappedBy=?)
	@OneToMany(mappedBy="department") //부서에 속한 팀들
	private List<TeamEntity> department = new ArrayList<>();
	*/
}
