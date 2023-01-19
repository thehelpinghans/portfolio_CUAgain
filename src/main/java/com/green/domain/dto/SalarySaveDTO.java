package com.green.domain.dto;

import com.green.domain.entity.SalaryEntity;
import com.green.domain.entity.SalaryStatus;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor

public class SalarySaveDTO {

	private Long id;
	private SalaryStatus s_status;
	private String payment;
	private Long employees_id;
	private Long tot_pay;


	@Builder
	public SalarySaveDTO(Long id, SalaryStatus s_status, String payment, Long employees_id, Long tot_pay) {
		this.id = id;
		this.s_status = s_status;
		this.payment = payment;
		this.employees_id = employees_id;
		this.tot_pay = tot_pay;
	}
	
	public SalaryEntity toEntity() {
		return SalaryEntity.builder()
				.id(id)
				.s_status(s_status)
				.payment(payment)
				.employees_id(employees_id)
				.tot_pay(tot_pay)
				.build();
	}
	
}