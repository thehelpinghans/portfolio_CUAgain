package com.green.chatbot.service.impl;

import com.green.chatbot.service.SalaryService;
import com.green.domain.dto.SalaryListDTO;
import com.green.domain.entity.SalaryEntity;
import com.green.domain.entity.SalaryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.EmployeesListDTO;
import com.green.domain.dto.SalaryInsertDTO;
import com.green.domain.entity.EmployeesEntityRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryServiceProcess implements SalaryService {
	
	
	@Autowired
	EmployeesEntityRepository empRepo;

	@Autowired
	SalaryEntityRepository salRepo;

	@Override
	public void searchEmp(Model model, long empId) {
		model.addAttribute("dto",empRepo.findById(empId).map(EmployeesListDTO::new).get());
	}

	//급여등록처리
	@Override
	public void salReg(SalaryInsertDTO dto) {
		salRepo.save(SalaryEntity.builder()
						.payDay(LocalDate.parse(dto.getPayDay()))
						.totPay(dto.getTotPay())
						.employee(empRepo.findById(dto.getEmpId()).get())
				.build());

	}

	//급여 조회 페이지이동
	@Override
	public void salList(Model model, long empId) {
		long totalPay=0;

		List<SalaryListDTO> list =  salRepo.findByEmployeeIdOrderByPayDayDesc(empId).stream().map(SalaryListDTO::new).collect(Collectors.toList());
		for(SalaryListDTO dto:list){
			totalPay+=dto.getTotPay();
		}
		model.addAttribute("myList",list);
		model.addAttribute("totalSum",totalPay);
	}

	//내 급여 조회 검색
	@Override
	public void salSearchList(long empId, Model model) {
		long totalPay=0;
		List<SalaryListDTO> list = salRepo.findByEmployeeIdOrderByPayDayDesc(empId).stream().map(SalaryListDTO::new).collect(Collectors.toList());
		for(SalaryListDTO dto:list){
			totalPay+=dto.getTotPay();
		}
		model.addAttribute("myList",list);
		model.addAttribute("totalSum",totalPay);
	}

	@Override
	public void salSearchList(LocalDate startDate, LocalDate endDate, long empId, Model model) {
		long totalPay=0;
		List<SalaryListDTO> list = salRepo.findByEmployeeIdAndPayDayBetweenOrderByPayDayDesc(empId,startDate,endDate).stream().map(SalaryListDTO::new).collect(Collectors.toList());
		for(SalaryListDTO dto:list){
			totalPay+=dto.getTotPay();
		}
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("myList",list);
		model.addAttribute("totalSum",totalPay);
	}

	@Override
	public void salDelete(long salId) {
		salRepo.deleteById(salId);
	}
}
