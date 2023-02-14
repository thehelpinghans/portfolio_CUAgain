package com.green.controller;

import com.green.chatbot.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class PageController {

	
	
	@Autowired
	private AttendService service;
	
    //대시보드 페이지(출퇴근 등록, 최근 근태리스트) 
    @GetMapping("/member/main")
    public String member(Principal principal, Model model){
    	long id=service.principalId(principal);
		service.attedList(id, model);
        return "admin/main";
    }

    //나의 근태현황 페이지(사원페이지)
    @GetMapping("/member/my_attendance")
    public String myAttendance(Principal principal, Model model){
    	long id=service.principalId(principal);
//    	service.myAttList(id, model)
        return "admin/attendance/my_attendance";
    }

//    //임시, 어드민페이지로 이동
//    @GetMapping("/admin/main")
//    public String admin(){
//        return "admin/main";
//    }

    //월 근태현황 페이지(어드민 페이지)
    @GetMapping("/admin/month_attendance")
    public String monthAttendance(Model model) {
        service.getDepartmentList(model);
        return "admin/attendance/month_attendance";
    }
    
    

  //부서관리 이동시 사원데이터 가져오기 
    @GetMapping("/member/department")
    public String department(){
        return "admin/depart/department";
    }
  //부서테스트이동
    @GetMapping("/admin/department3")
    public String departTest(){
        return "admin/depart/department3";
    }




    @PostMapping("/comm/checkRole")
    public String checkRole(){
        return "admin/main";
    }
    
    // 점포추가 작성 페이지
    @GetMapping("/admin/store-write")
    private String storedetail() {
    	return "/admin/store/store-write";
    }
}
