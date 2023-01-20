package com.green.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.SalaryResponseDTO;
import com.green.domain.dto.SalarySaveDTO;
import com.green.domain.dto.SalaryUpdateDTO;
import com.green.domain.entity.SalaryEntity;

@Service

public interface SalaryService {
	
	/*
	 * Long register(SalarySaveDTO dto);
	 * 
	 * default SalaryEntity dtoToEntity(SalarySaveDTO dto) { return null; }
	 */

	void salarySave(SalarySaveDTO dto);
	
	void salaryList(Model model);
	
	void salaryUpdate(SalaryUpdateDTO dto);

	void searchEmp(Model model, long empId);
	

	/*
	 * static Long update(Long id, SalaryUpdateDTO requestDto) {
	 * 
	 * return null; }
	 * 
	 * static SalaryResponseDTO findById(Long id) {
	 * 
	 * return null; }
	 * 
	 * static void delete(Long id) {
	 * 
	 * 
	 * }
	 */
}
