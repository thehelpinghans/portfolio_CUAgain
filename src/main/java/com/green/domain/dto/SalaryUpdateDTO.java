package com.green.domain.dto;

import com.green.domain.entity.SalaryStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SalaryUpdateDTO {
	private SalaryStatus s_status;
	private Long tot_pay;


	@Builder
	public SalaryUpdateDTO(SalaryStatus s_status, Long tot_pay) {
		this.s_status = s_status;
		this.tot_pay = tot_pay;
	}
}