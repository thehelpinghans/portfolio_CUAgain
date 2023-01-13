package com.green.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.domain.dto.AttendanceInsertDTO;
import com.green.domain.dto.AttendanceUpdateDTO;
import com.green.security.MyUserDetails;
import com.green.service.AttendService;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendService service;
	
	//출근 저장
	@PostMapping("/member/attendance/reg")
	public String attReg(Principal principal, AttendanceInsertDTO dto, AttendanceUpdateDTO udto) {

		long id=service.principalId(principal);
		service.save(id, dto, udto); //저장
		
		
		//service.findByOnTime(id); //찾기
		
		return "member/main";
	}
}
