package com.green.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.EmployeesListDTO;
import com.green.domain.dto.SalaryResponseDTO;
import com.green.domain.dto.SalarySaveDTO;
import com.green.domain.dto.SalaryUpdateDTO;
import com.green.domain.entity.EmployeesEntityRepository;
import com.green.service.SalaryService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SalaryServiceProcess implements SalaryService{
	
	
	@Autowired
	EmployeesEntityRepository empRepo;
	
	@Override
	public void salarySave(SalarySaveDTO dto) {
		
	}

	@Override
	public void salaryUpdate(SalaryUpdateDTO dto) {
		
	}

	@Override
	public void salaryList(Model model) {
		
		
	}


	@Override
	public void searchEmp(Model model, long empId) {
		model.addAttribute("dto",empRepo.findById(empId).map(EmployeesListDTO::new).get());
	}
	
	
	
	
		

	
	
	
	/*
	 * @Override public Long register(SalarySaveDTO dto) { return null; }
	 */
	
	

}
