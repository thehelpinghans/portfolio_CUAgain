package com.green.domain.dto;

import java.time.LocalDateTime;

import com.green.domain.entity.EmployeesEntity;
import com.green.domain.entity.Position;
import com.green.domain.entity.SalaryEntity;

import lombok.Getter;

@Getter
public class SalaryResponseDTO {
	private Long id;
	private LocalDateTime payday;
	private EmployeesEntity employee;
	private Long totPay;
	
	public SalaryResponseDTO(SalaryEntity entity) {
		this.id           = entity.getId();
		this.payday       = entity.getPayday();
		this.employee     = entity.getEmployee();
		this.totPay      = entity.getTotPay();
	}
}
