package com.green.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SalaryUpdateDTO {
	private Long tot_pay;


	@Builder
	public SalaryUpdateDTO(Long tot_pay) {
		this.tot_pay = tot_pay;
	}
}