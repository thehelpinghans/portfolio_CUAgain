package com.green.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.service.StoreService;
import com.green.domain.dto.StoreSaveDTO;
import com.green.domain.entity.AddressEntityRepository;
import com.green.domain.entity.EmployeesEntityRepository;
import com.green.domain.entity.StoreEntity;
import com.green.domain.entity.StoreEntityRepository;

@Service
public class StoreServiceProcess implements StoreService {
	
	@Autowired
	StoreEntityRepository storeRepo;
	
	@Autowired
	EmployeesEntityRepository employeesRepo;
	
	@Autowired
	AddressEntityRepository addressRepo;
	
	@Override
	public void getlist(Model model) {
		model.addAttribute("list",storeRepo.findAll());
	}
	
	@Transactional
	@Override
	public void save(StoreSaveDTO dto, String Name) {
		StoreEntity store = storeRepo.save(StoreEntity.builder()
//				.manager(employeesRepo.findByName(Name).orElseThrow())
//				.address(dto.getAddress())
				.content(dto.getContent())
				.name(dto.getName()).build());
		
	}
	
	
}
