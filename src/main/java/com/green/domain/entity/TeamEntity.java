package com.green.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "team")
@Entity
public class TeamEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;   //팀번호
	private String name; //팀이름
	
	@OneToOne //담당자
	@JoinColumn
	private EmployeesEntity manager;
	/*
	@Builder.Default//테이블이 중복으로 생기지않기위해 (mappedBy=?)
	@OneToMany(mappedBy="team") //팀에 소속 사원들
	private List<EmployeesEntity> employees = new ArrayList<>();
	//사원정보는 리스트로 받겠다?
	public TeamEntity team_em(EmployeesEntity employees) {
		this.employees.add(employees);
		return this;
	}
	*/
	@ManyToOne//상위 조직 부서 dep_id
	@JoinColumn
	private DepartmentEntity dep;
}
