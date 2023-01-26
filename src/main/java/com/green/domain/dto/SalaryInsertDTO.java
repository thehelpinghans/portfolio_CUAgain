package com.green.domain.dto;

import lombok.Data;

@Data
public class SalaryInsertDTO {

	private String payDay;
	private long empId;
	private long totPay;

}