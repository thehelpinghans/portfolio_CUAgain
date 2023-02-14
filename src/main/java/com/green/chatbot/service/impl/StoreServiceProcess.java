package com.green.chatbot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.green.chatbot.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.StoreListDTO;
import com.green.domain.dto.StoreSaveDTO;
import com.green.domain.entity.AddressEntity;
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
				.manager(employeesRepo.findById(dto.getManagerId()).orElseThrow())
				.content(dto.getContent())
				.name(dto.getName()).build());
	}

	/**
	 * 기본 페이지 -> 페이징 처리
	 */
	@Transactional
	@Override
	public void getlist(Model model, int page) {
		int size=5;
		Sort sort=Sort.by(Direction.DESC, "managerId");
		Pageable pageable=PageRequest.of(page-1, size, sort);
		Page<StoreEntity> result = storeRepo.findAll(pageable);
		
		int nowPage = result.getNumber()+1;
		int startPage = Math.max(nowPage -4, 1);
		int endPage = Math.min(nowPage +5, result.getTotalPages());
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("p",result);
		
		List<StoreListDTO> list = result.stream().map(StoreListDTO :: new).collect(Collectors.toList());
		model.addAttribute("list" , list);
		
	}

	@Transactional
	@Override
	public void detail(long id, Model model) {
		model.addAttribute("dto",storeRepo.findById(id).map(StoreSaveDTO :: new).orElseThrow());

	}

	@Override
	public void delete(long id) {
		storeRepo.deleteById(id);
	}

	@Transactional
	@Override
	public void update(StoreSaveDTO dto, long id, long employeeId) {
		System.out.println(">>>수자ㅓㅇ");
		storeRepo.findById(id).map(e->e.update(dto));

	}
	/**
	 * 검색 이후 페이징 처리
	 */
	@Override
	public void search(String name, String type, Model model, int page) {
		
		int size=5;
		
		Sort sort=Sort.by(Direction.DESC, "managerId");
		Pageable pageable=PageRequest.of(page-1, size, sort);
		Page<StoreEntity> result = storeRepo.findAll(pageable);
		
		System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+result.getNumber());
		if(type.equals("Name")) {
			 result = storeRepo.findByNameContaining(name,pageable);
		}else {
			result = storeRepo.findByManagerNameContaining(name,pageable);
		}
		
		int nowPage = result.getNumber()+1;
		int startPage = Math.max(nowPage -4, 1);
		int endPage = Math.min(nowPage +5, result.getTotalPages());
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("type", type);
		model.addAttribute("name", name);
		model.addAttribute("p",result);
		
		model.addAttribute("list", result.map(StoreSaveDTO::new));

		System.err.println("정상실행");
	}

}
