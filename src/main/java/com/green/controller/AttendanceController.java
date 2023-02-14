package com.green.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.domain.dto.AdminAttendanceListDTO;
import com.green.domain.dto.AttendanceCalendarDTO;
import com.green.domain.dto.AttendanceDetailDTO;
import com.green.domain.dto.AttendanceListDTO;
import com.green.domain.dto.AttendanceListRequestDTO;
import com.green.chatbot.service.AttendService;

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
	
	//나의 근태 리스트+페이징
	@GetMapping("/member/myAttList")
	@ResponseBody
	public List<AttendanceListDTO> attList(Principal principal, Pageable pageable, @ModelAttribute AttendanceListRequestDTO dto) {
		System.out.println(pageable.toString());
		System.out.println(dto.toString());
		long id=service.principalId(principal);
		List<AttendanceListDTO> result= service.getList(id, pageable , dto);
		
		
		
		return result;
	}
	

	//일 근태현황 페이지(어드민 페이지)
    @GetMapping("/admin/day_attendance")
    public String dayAttendance(Model model, AdminAttendanceListDTO dto, @RequestParam(defaultValue = "1")int page) {
		service.adminList(model, dto, page);
    	
    	return "admin/attendance/day_attendance";
    }
	
	//관리자페이지 근태관리 검색(부서, 이름)
	@GetMapping("/admin/day_attendance/search")
    public String search(String keyword, String department, String start, String end, Model model, @RequestParam(defaultValue = "1")int page) {
    System.out.println(start);  
    
	service.search(keyword,department,start,end,model,page);
       System.out.println(department);
       System.out.println(keyword);
       return "admin/attendance/attendSearch";
    }
	
	//관리자 근태관리 수정 페이지(사원이름을 클릭했을 때)
	@GetMapping("/admin/day_attendance/update")
	public String attendUpdate() {
		return "/admin/attendance/attendanceUpdate";
	}
	
	// 월 근태 현황
    @GetMapping("month/attendance")
    @ResponseBody
    public List<AttendanceDetailDTO> getMonthAttendance(@ModelAttribute AttendanceCalendarDTO dto){
        System.out.println(dto);
        List<AttendanceDetailDTO> attendanceDetailDTOs = service.getAttendance(dto);
        return attendanceDetailDTOs;
    }
    
    @GetMapping("/admin/scheduleCalendar")
    public String schedule() {
    	return "admin/attendance/scheduleCalendar";
    }
}
