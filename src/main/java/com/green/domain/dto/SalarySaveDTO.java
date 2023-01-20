package com.green.domain.dto;

import java.time.LocalDateTime;

import com.green.domain.entity.EmployeesEntity;
import com.green.domain.entity.SalaryEntity;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor

public class SalarySaveDTO {

	private Long id;
	private LocalDateTime payday;
	private EmployeesEntity employee;
	private Long totPay;


	@Builder
	public SalarySaveDTO(Long id, LocalDateTime payday, EmployeesEntity employee, Long totPay) {
		this.id = id;
		this.payday = payday;
		this.employee = employee;
		this.totPay = totPay;
	}
	
	public SalaryEntity toEntity() {
		return SalaryEntity.builder()
				.id(id)
				.payday(payday)
				.employee(employee)
				.totPay(totPay)
				.build();
	}
	
}