package com.green.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.domain.dto.AdminAttendanceListDTO;
import com.green.domain.dto.AttendanceListRequestDTO;
import com.green.security.MyUserDetails;
import com.green.service.AttendService;
import com.green.service.DepartmentService;
import com.green.service.TeamService;

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
        return "member/attendance/my_attendance";
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
    @GetMapping("/admin/department")
    public String department(){
        return "admin/depart/department";
    }
  //부서테스트이동
    @GetMapping("/admin/department3")
    public String departTest(){
        return "admin/depart/department3";
    }


 // 급여관리의 등록 페이지로 이동
    @GetMapping("/admin/add")
    public String salaryAdd(){
        return "/admin/salary/add";
    }
    
    // 급여관리의 조회/수정 페이지로 이동
    @GetMapping("/admin/edit")
    public String salaryEdit(){
        return "/admin/salary/edit";
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
