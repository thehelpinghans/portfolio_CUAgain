package com.green.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.domain.dto.AttendanceInsertDTO;
import com.green.service.AttendService;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendService service;
	
	@PostMapping("/member/attendance/reg/")
	public String attReg(long id, AttendanceInsertDTO dto) {

		
		service.save(id, dto);
		
		return "member/main";
	}
}
