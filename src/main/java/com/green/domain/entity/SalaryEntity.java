package com.green.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Salary")

public class SalaryEntity {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private SalaryStatus s_status;
	
	@Column(length = 255)
	private String payment;
	
	@Column(unique = true, nullable = false)
	private Long employees_id;
	
	@Column
	private Long tot_pay;

	@Enumerated(EnumType.STRING)
	private Position position;
	
	@Builder
	public SalaryEntity(Long id, SalaryStatus s_status, String payment, Long employees_id, Long tot_pay, Position position) {
		this.id = id;
		this.s_status = s_status;
		this.payment = payment;
		this.employees_id = employees_id;
		this.tot_pay = tot_pay;
		this.position = position;
	}
	
	public void update(SalaryStatus s_status, Long tot_pay) {
		this.s_status = s_status;
		this.tot_pay = tot_pay;
	}
	
}
