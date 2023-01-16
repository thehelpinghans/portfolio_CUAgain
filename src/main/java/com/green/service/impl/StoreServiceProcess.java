package com.green.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.StoreListDTO;
import com.green.domain.dto.StoreSaveDTO;
import com.green.domain.entity.AddressEntity;
import com.green.domain.entity.AddressEntityRepository;
import com.green.domain.entity.EmployeesEntityRepository;
import com.green.domain.entity.StoreEntity;
import com.green.domain.entity.StoreEntityRepository;
import com.green.service.StoreService;

@Service
public class StoreServiceProcess implements StoreService {
	
	@Autowired
	StoreEntityRepository storeRepo;
	
	@Autowired
	EmployeesEntityRepository employeesRepo;
	
	@Autowired
	AddressEntityRepository addressRepo;
	
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

	@Transactional
	@Override
	public void getlist(Model model) {
		List<StoreEntity> result = storeRepo.findAll();
		List<StoreListDTO> list = result.stream().map(StoreListDTO :: new).collect(Collectors.toList());
		model.addAttribute("list" , list);
		
	}

}
