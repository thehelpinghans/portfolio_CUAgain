package com.green.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.TeamDTO;
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
	
	//팀 데이터 모델에 담아서 가져가기
	@Override
	public void getTeamList(Model model) {
		List<TeamEntity> result = teamRepo.findAll();
		List<TeamDTO> teamList= result.stream().map(TeamDTO::new).collect(Collectors.toList());
		model.addAttribute("teamList", teamList);
	}
	
	//팀 데이터 등록하기 
	@Override
	public void save(TeamDTO dto , long dep_id) {
		System.err.println("팀등록");
		teamRepo.save(TeamEntity.builder()
				.id(dto.getId())//team id
				.name(dto.getName()) //team name 
				.build()
				.teamEntity(depRepo.findById(dep_id).orElseThrow())
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
	
	

	

}