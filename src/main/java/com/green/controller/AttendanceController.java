package com.green.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.domain.dto.AdminAttendanceListDTO;
import com.green.domain.dto.AttendanceListDTO;
import com.green.domain.dto.AttendanceListRequestDTO;
import com.green.domain.entity.AttendanceEntity;
import com.green.service.AttendService;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendService service;
	
	//출근 저장
	@PostMapping("/member/attendance/in")
	@ResponseBody
	public String attIn(Principal principal, Model model) {
		
		long id=service.principalId(principal);
		String result = service.save(id); //저장
		
		//service.findByOnTime(id); //찾기
		
		// return "member/main";
		return result;
	}
	
	//퇴근 저장
	@PostMapping("/member/attendance/out")
	@ResponseBody
	public String attOut(Principal principal) {
		
		long id=service.principalId(principal);
		String result = service.updateOut(id); //저장
		
		
		//service.findByOnTime(id); //찾기
		
		// return "member/main";
		return result;
	}
	
	//근태 리스트+페이징
	@GetMapping("/member/myAttList")
	@ResponseBody
	public List<AttendanceListDTO> attList(Principal principal, Pageable pageable, @ModelAttribute AttendanceListRequestDTO dto) {
		System.out.println(pageable.toString());
		System.out.println(dto.toString());
		long id=service.principalId(principal);
		List<AttendanceListDTO> result= service.getList(id, pageable , dto);
		
		
		
		return result;
	}
	
	//관리자페이지 검색
	@GetMapping("/admin/day_attendance/search")
    public String search(String keyword, String department, Model model) {
       service.search(keyword,model,department);
 
       return "admin/attendance/attendSearch";
    }
	
	
	
}
