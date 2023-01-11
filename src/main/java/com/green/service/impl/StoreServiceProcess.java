package com.green.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.service.StoreService;
import com.green.domain.dto.StoreSaveDTO;
import com.green.domain.entity.EmployeesEntityRepository;
import com.green.domain.entity.StoreEntityRepository;

@Service
public class StoreServiceProcess implements StoreService {
	
	@Autowired
	StoreEntityRepository storeRepo;
	
	@Autowired
	EmployeesEntityRepository employeesRepo;
	
	@Override
	public void getlist(Model model) {
		model.addAttribute("list",storeRepo.findAll());
	}

	@Override
	public void save(StoreSaveDTO dto, long id) {
		
		
	}
	
	
}
