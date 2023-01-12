package com.green.domain.dto;

import com.green.domain.entity.SalaryStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SalaryDTO {

	private Long id;
	private SalaryStatus s_status;
	private String payment;
	private Long employees_id;
	private Long tot_pay;
}
