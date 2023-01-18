package com.green.service.impl;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.TeamDTO;
import com.green.domain.entity.DepartmentEntity;
import com.green.domain.entity.DepartmentEntityRepository;
import com.green.domain.entity.TeamEntity;
import com.green.domain.entity.TeamEntityRepository;
import com.green.service.TeamService;

@Service
public class TeamServiceProc implements TeamService{

	@Autowired
	TeamEntityRepository teamRepo;
	
	@Autowired
	DepartmentEntityRepository depRepo;
	
	
	
	//팀 데이터 등록하기 
	@Override
	public void save(TeamDTO dto , long dep_id) {
		System.err.println("팀등록");
		teamRepo.save(TeamEntity.builder()
				.name(dto.getName()) //team name 
				.dep(DepartmentEntity.builder().id(dep_id).build())
				.build()
				//.teamEntity(depRepo.findById(dep_id).orElseThrow())
				);
	}

	@Override
	public void getTeamListOfDep(long depId, Model model) {
		// 해당하는 부서의 팀 리스트를 모델에 담기
		model.addAttribute("teamList", teamRepo.findByDepId(depId)//List<TeamEntity>
											   .stream()//Stream<TeamEntity>
											   .map(e->e.getName())
											   .collect(Collectors.toList()));
		
	}
	//팀 삭제기능
	@Transactional
	@Override
	public void teamDelete(long id) {
		teamRepo.deleteById(id);
	}
	
	

	

}