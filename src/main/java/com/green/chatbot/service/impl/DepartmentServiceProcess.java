package com.green.chatbot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.green.chatbot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.DepartmentDTO;
import com.green.domain.dto.EmployeesListDTO;
import com.green.domain.dto.TeamDTO;
import com.green.domain.entity.DepartmentEntity;
import com.green.domain.entity.DepartmentEntityRepository;
import com.green.domain.entity.EmployeesEntity;
import com.green.domain.entity.EmployeesEntityRepository;
import com.green.domain.entity.TeamEntityRepository;

@Service
public class DepartmentServiceProcess implements DepartmentService {

	@Autowired
	DepartmentEntityRepository depRepo;
	
	@Autowired
	TeamEntityRepository teamRepo;
	
	@Autowired
	EmployeesEntityRepository empRepo;
	
	//부서+팀 데이터 담아가기
	@Override
	public void getList(Model model) {
		List<DepartmentEntity> result = depRepo.findAll();//result =  List<DepartmentEntity> 
		List<DepartmentDTO> list = result.stream().map(e->{
				DepartmentDTO dto=new DepartmentDTO(e);
				dto.setTeams(
				teamRepo.findAllBydep(e).stream().map(TeamDTO::new).collect(Collectors.toList())
				);
				return dto;
			}).collect(Collectors.toList());
				//list = List<DepartmentDTO>			//미리 만든 생성자에 데려오는 리스트의 각 컬럼에다가 넣어주겠다!
		model.addAttribute("list", list);
		//모델에 담아서 페이지에 보낸다.
		//List<TeamEntity> teamResult = teamRepo.findAll();
		//List<TeamDTO> teamlist = teamResult.stream().map(TeamDTO::new).collect(Collectors.toList());
		//model.addAttribute("teamlist", teamlist);
		
	}
	//부서등록
	@Override
	public void save(DepartmentDTO dto) {
		depRepo.save(DepartmentEntity.builder()
				.name(dto.getName())
				.build());
	}
	//부서 수정기능
	@Transactional
	@Override
	public String depUpdate(long depId, String departmentName) {
		depRepo.findById(depId).map(e->e.updateDepartmentName(departmentName)).orElseThrow();
		return departmentName;
				
	}
	//부서 삭제기능
	@Transactional
	@Override
	public void depDelete(long depId) {
		//부서ID랑 일치하는 팀삭제
		teamRepo.deleteAllByDep_id(depId);//팀에 있는 부서id가 먼저 삭제되어야함(순서)
		//부서삭제
		depRepo.deleteById(depId);//그 이후 부서삭제 
		//현재 해당부서에 사원이 존재하지않을 경우에만 삭제가 가능하다.
		//만약 사원이 있는 부서를 삭제하려고한다면 사원정보가 있는 부서는 사원정보를 먼저 제거하라고 해주기.
	}
	//모든 사원리스트가져오기
	@Override
	public void getEmpList(Model model) {
		List<EmployeesEntity> result = empRepo.findAll();
		List<EmployeesListDTO> empList = result.stream().map(EmployeesListDTO::new).collect(Collectors.toList());
		model.addAttribute("title","CUAgain");
		model.addAttribute("empList", empList);
	}
//	//부서소속 사원 리스트 가져오기
//	@Override
//	public void getDepEmpList(long id, Model model) {
//		List<EmployeesEntity> result = empRepo.findByDepId(id);
//		List<EmployeesListDTO> depEmpList = result.stream().map(EmployeesListDTO::new).collect(Collectors.toList());
//		model.addAttribute("title","name");
//		model.addAttribute("empList", depEmpList);
//	}
	
	
	

}