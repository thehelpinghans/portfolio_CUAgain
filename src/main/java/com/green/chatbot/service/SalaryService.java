package com.green.chatbot.service;

import com.green.domain.dto.SalaryInsertDTO;
import org.springframework.ui.Model;

import java.time.LocalDate;


public interface SalaryService {
	
	/*
	 * Long register(SalarySaveDTO dto);
	 * 
	 * default SalaryEntity dtoToEntity(SalarySaveDTO dto) { return null; }
	 */


	void searchEmp(Model model, long empId);

	void salReg(SalaryInsertDTO dto);

	void salList(Model model, long id);

	void salSearchList(long empId, Model model);

	void salSearchList(LocalDate startDate, LocalDate endDate, long empId, Model model);

	void salDelete(long salId);


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
