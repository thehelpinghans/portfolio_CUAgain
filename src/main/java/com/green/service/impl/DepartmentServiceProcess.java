package com.green.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.DepartmentDTO;
import com.green.domain.entity.DepartmentEntity;
import com.green.domain.entity.DepartmentEntityRepository;
import com.green.service.DepartmentService;

@Service
public class DepartmentServiceProcess implements DepartmentService{

	@Autowired
	DepartmentEntityRepository depRepo;
	
	//부서 데이터 보여주기
	@Override
	public void getList(Model model) {
		List<DepartmentEntity> result = depRepo.findAll();//result =  List<DepartmentEntity> 
		List<DepartmentDTO> list = result.stream().map(DepartmentDTO::new).collect(Collectors.toList());
				//list = List<DepartmentDTO>			//미리 만든 생성자에 데려오는 리스트의 각 컬럼에다가 넣어주겠다!
		model.addAttribute("list", list);
		//모델에 담아서 페이지에 보냈다.
	}
	
	
//	List<DepartmentEntity> result;
//	@Override
//	public void getList(String name, Model model) {
//		getList(depRepo.findByname(name).get(1));
//		
//		model.addAttribute("result",result );
//		model.addAttribute("list", depRepo.findByname(name)
//				.stream()
//				.map(null)
//				.collect(Collectors.toList()));
//	}
//	
	

}
