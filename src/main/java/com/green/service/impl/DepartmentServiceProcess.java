package com.green.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.DepartmentDTO;
import com.green.domain.entity.DepartmentEntity;
import com.green.domain.entity.DepartmentEntityRepository;
import com.green.domain.entity.EmployeesEntity;
import com.green.domain.entity.EmployeesEntityRepository;
import com.green.domain.entity.TeamEntityRepository;
import com.green.service.DepartmentService;

@Service
public class DepartmentServiceProcess implements DepartmentService{

	@Autowired
	DepartmentEntityRepository depRepo;
	
	@Autowired
	TeamEntityRepository teamRepo;
	
	@Autowired
	EmployeesEntityRepository empRepo;
	
	//부서 데이터 보여주기
	@Override
	public void getList(Model model) {
		List<DepartmentEntity> result = depRepo.findAll();//result =  List<DepartmentEntity> 
		List<DepartmentDTO> list = result.stream().map(DepartmentDTO::new).collect(Collectors.toList());
				//list = List<DepartmentDTO>			//미리 만든 생성자에 데려오는 리스트의 각 컬럼에다가 넣어주겠다!
		model.addAttribute("list", list);
		//모델에 담아서 페이지에 보냈다.
	}
	//부서 수정기능
	@Transactional
	@Override
	public String depUpdate(long depId, String departmentName) {
		depRepo.findById(depId).map(e->e.updateDepartmentName(departmentName)).orElseThrow();
				
		return departmentName;
				
	}
	//부서 삭제기능
	@Override
	public void depDelete(long depId) {
		empRepo.deleteById(depId);
		teamRepo.deleteById(depId);
		depRepo.deleteById(depId);
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
