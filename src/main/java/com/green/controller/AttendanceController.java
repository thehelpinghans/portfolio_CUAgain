package com.green.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.domain.dto.AttendanceInsertDTO;
import com.green.domain.dto.AttendanceUpdateDTO;
import com.green.service.AttendService;

@Controller
public class AttendanceController {
	
	@Autowired
	private AttendService service;
	
	//출근 저장
	@PostMapping("/member/attendance/reg/{id}")
	public String attReg(@PathVariable long id, AttendanceInsertDTO dto, AttendanceUpdateDTO udto) {

		
		service.save(id, dto, udto); //저장
		
		//service.findByOnTime(id); //찾기
		
		return "member/main";
	}
}
