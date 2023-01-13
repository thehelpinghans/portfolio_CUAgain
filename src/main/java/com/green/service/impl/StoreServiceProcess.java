package com.green.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.service.StoreService;
import com.green.domain.dto.StoreRegDTO;
import com.green.domain.dto.StoreSaveDTO;
import com.green.domain.entity.AddressEntity;
import com.green.domain.entity.AddressEntityRepository;
import com.green.domain.entity.AttendStatus;
import com.green.domain.entity.EmployeesEntity;
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
	public void save(StoreSaveDTO dto) {
		storeRepo.save(StoreEntity.builder()
				.address(AddressEntity.builder()
						.detailAddress(dto.getDetailAddress())
						.extraAddress(dto.getExtraAddress())
						.jibunAddress(dto.getJibunAddress())
						.postcode(dto.getPostcode())
						.roadAddress(dto.getRoadAddress())
						.build())
				.manager(employeesRepo.findById(dto.getManagerId()).orElseThrow())//
				.content(dto.getContent())
				.name(dto.getName()).build());
	}

}
