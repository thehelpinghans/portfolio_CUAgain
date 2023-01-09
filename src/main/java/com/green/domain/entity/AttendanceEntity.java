package com.green.domain.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "attendance")
@Entity
public class AttendanceEntity extends BaseDateEntity{
	//근태pk, 사원번호, 부서, 직책, 사원명, 상태 , 출퇴근기록(BaseDateEntity)
	
	//근태pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	//사원번호, 사원명
	@JoinColumn
	@ManyToOne
	private EmployeesEntity employee;
	
	//부서
	@JoinColumn
	@ManyToOne
	private DepartmentEntity department;
	
	//직책
	@Enumerated(EnumType.STRING)
	private Position position;
	
	//상태
	@Enumerated(EnumType.STRING)
	private AttendStatus attendStatus;
	

}
