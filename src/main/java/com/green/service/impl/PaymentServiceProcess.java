package com.green.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.service.PaymentService;
import com.green.domain.dto.PaymentDTO;
import com.green.domain.entity.EmployeesEntityRepository;
import com.green.domain.entity.PaymentEntity;
import com.green.domain.entity.PaymentEntityRepository;

@Service
public class PaymentServiceProcess implements PaymentService {
	
	@Autowired
	PaymentEntityRepository paymentRepo;
	
	@Autowired
	EmployeesEntityRepository employeesRepo;


	@Override
	public void getlist(Model model) {
		model.addAttribute("list", paymentRepo.findAll());
	}

	@Transactional
	@Override
	public void save(PaymentDTO dto) {
		PaymentEntity payment = paymentRepo.save(PaymentEntity.builder()
//				.employees_id(employeesRepo.findByName(Name).orElseThrow())
				.title(dto.getTitle())
				.content(dto.getContent())
//				.emp_id(employeesRepo.findByName(Name).orElseThrow())
				.status(0)
				.build());
		
	}

	

	
	
	
}
