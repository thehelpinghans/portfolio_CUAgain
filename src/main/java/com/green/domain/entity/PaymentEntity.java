package com.green.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agreement")
@Entity
public class PaymentEntity extends BaseDateEntity{
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	@Column(nullable = false)
	private String title; //문서이름
	
	@Lob
	@Column(nullable = false)
	private String content; //문서내용
	
	@Column(nullable = false)
	private long status; //문서상태 0:예정,1:대기,2:승인,3:반려
	
	@JoinColumn(name = "employees_id", insertable = false, updatable = false) //employees_id
	@ManyToOne
	private EmployeesEntity employees_id; //사원번호(작성자)
	
	@JoinColumn(name = "employees_id")
	@Column(nullable = false)
	private long emp_id; //결재자
}
