package com.green.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.service.DocumentService;
import com.green.domain.dto.PaymentDTO;
import com.green.domain.entity.EmployeesEntityRepository;
import com.green.domain.entity.DocumentEntity;
import com.green.domain.entity.DocumentEntityRepository;

@Service
public class DocumentServiceProcess implements DocumentService {
	
	@Autowired
	DocumentEntityRepository docRepo;
	
	@Autowired
	EmployeesEntityRepository employeesRepo;


	@Override
	public void getlist(Model model) {
		model.addAttribute("list", docRepo.findAll());
	}

	@Transactional
	@Override
	public void save(PaymentDTO dto) {
		DocumentEntity payment = docRepo.save(DocumentEntity.builder()
//				.employees_id(employeesRepo.findByName(Name).orElseThrow())
				.title(dto.getTitle())
				.content(dto.getContent())
//				.emp_id(employeesRepo.findByName(Name).orElseThrow())
				.status(0)
				.build());
		
	}

	

	
	
	
}
