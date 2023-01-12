package com.green.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.green.domain.dto.AttendanceInsertDTO;
import com.green.domain.dto.AttendanceUpdateDTO;


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
public class AttendanceEntity{
	//근태pk, 사원번호, 부서, 팀, 직책, 사원명, 날짜, 출근시간, 퇴근시간, 상태 
	
	//근태pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	//사원번호
	@JoinColumn
	@ManyToOne
	private EmployeesEntity employee;

	//날짜
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDate date;
	
	//출근시간
	@Column
	@CreationTimestamp
	private LocalDateTime inTime;
	
	//퇴근시간
	@Column
	@UpdateTimestamp
	private LocalDateTime outTime; 
	
	//상태
	@Builder.Default
	@CollectionTable(name = "attend_status")
	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<AttendStatus> status=new HashSet<>();
	
	public AttendanceEntity addStatus(AttendStatus atStatus) {
		status.add(atStatus);
		return this;
	}
	

	public AttendanceEntity employeeId(EmployeesEntity employee) {
		this.employee = employee;
		return this;
	}

	public AttendanceEntity update(AttendanceUpdateDTO udto) {
		this.outTime= udto.getOutTime();
		return this;
	}

}
