package com.green.domain.dto;

import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import com.green.domain.entity.Position;
import com.green.domain.entity.SalaryEntity;
import com.green.domain.entity.SalaryStatus;

import lombok.Getter;

@Getter
public class SalaryResponseDTO {
	private Long id;
	private SalaryStatus s_status;
	private String payment;
	private Long employees_id;
	private Long tot_pay;
	private Position position;
	
	public SalaryResponseDTO(SalaryEntity entity) {
		this.id           = entity.getId();
		this.s_status     = entity.getS_status();
		this.payment      = entity.getPayment();
		this.employees_id = entity.getEmployees_id();
		this.tot_pay      = entity.getTot_pay();
		this.position     = entity.getPosition();
	}
}
