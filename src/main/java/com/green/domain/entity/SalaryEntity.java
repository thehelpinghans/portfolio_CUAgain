package com.green.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "salary")
public class SalaryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private LocalDateTime payday;
	
	@JoinColumn	//employee_id
	@ManyToOne
	private EmployeesEntity employee;
	
	private Long totPay;

	/*
	 * public SalaryEntity(SalaryInsertDTO e) {
	 * 
	 * this.payday = e.getPayday(); this.payment = payment; this.employees_id =
	 * employees_id; this.tot_pay = tot_pay; this.position = position; }
	
	
	 * public void update(SalaryStatus s_status, Long tot_pay) { this.s_status =
	 * s_status; this.tot_pay = tot_pay; }
	 */
}
