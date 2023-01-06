package com.green.domain.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
public class AttendanceEntity {
	//근태pk, 사원번호, 부서, 직책, 사원명, 상태, 출근일자, 출근시간, 퇴근일자, 퇴근시간, 상세내역
	
	//pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	//사원번호
	@JoinColumn
	@ManyToOne
	private EmployeesEntity employee;
	
	//부서
	
	//직책
	@Builder.Default
	@CollectionTable(name = "position")
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Position> position = new HashSet<>();
	
	public AttendanceEntity addPositon(Position positions) {
		position.add(positions);
		return this;
	}
	
	//상태
	@Builder.Default
	@CollectionTable(name = "status")
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Status> status = new HashSet<>();
	
	public AttendanceEntity addStatus(Status sta) {
		status.add(sta);
		return this;
	}
	
	//출근일자
	private LocalDate onDay;
	
	//출근시간
	private LocalTime onTime;
	
	//퇴근일자
	private LocalDate offDay;
	
	//퇴근시간
	private LocalTime offTime;

}
