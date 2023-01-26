package com.green.domain.dto;

import com.green.domain.entity.SalaryEntity;
import lombok.Data;

@Data
public class SalaryListDTO {

	private long id;
	private String payDay;
	private long empId;
	private String empName;
	private String empDep;
	private String empTeam;
	private String empPosition;
	private long totPay;

	public SalaryListDTO(SalaryEntity e){
		this.id=e.getId();
		this.payDay= String.valueOf(e.getPayDay());
		this.empId=e.getEmployee().getId();
		this.empName=e.getEmployee().getName();
		this.empDep=e.getEmployee().getDep().getName();
		this.empTeam=e.getEmployee().getTeam().getName();
		this.empPosition=e.getEmployee().getPosition().name();
		this.totPay=e.getTotPay();
	}
}