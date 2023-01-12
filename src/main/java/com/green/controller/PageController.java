package com.green.controller;

import com.green.security.MyUserDetails;
import com.green.security.MyUserDetailsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.domain.dto.DepartmentDTO;
import com.green.service.DepartmentService;

@Controller
public class PageController {

	@Autowired
	DepartmentService depaService;

    //임시, 사원페이지로 이동
    @GetMapping("/member/main")
    public String member(){
        return "member/main";
    }

    //나의 근태현황 페이지(사원페이지)
    @GetMapping("/member/my_attendance")
    public String myAttendance(){
        return "member/attendance/my_attendance.html";
    }

    //임시, 어드민페이지로 이동
    @GetMapping("/admin/main")
    public String admin(){
        return "admin/main";
    }

    //일 근태현황 페이지(어드민 페이지)
    @GetMapping("/admin/day_attendance")
    public String dayAttendance() {
    	return "admin/attendance/day_attendance";
    }

    //월 근태현황 페이지(어드민 페이지)
    @GetMapping("/admin/month_attendance")
    public String monthAttendance() {
    	return "admin/attendance/month_attendance";
    }
  //부서관리 이동
    @GetMapping("/admin/department")
    public String department(Model model){
    	depaService.getList(model);
        return "admin/depart/department";
    }
//    //팀명가져올거임
//    @PostMapping("/admin/teamList")
//    public String teamList(Model model){
//    	teamService.getList(model);
//        return "";
//    }
  //부서테스트이동
    @GetMapping("/admin/department3")
    public String departTest(){
        return "admin/depart/department3";
    }

    //임시, 결재페이지로 이동
    @GetMapping("admin/payment")
    public String payMent() {
    	return "admin/payment/payment";
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
    
    @GetMapping("admin/payment-write")
    private String payMentWrite() {
    	return "admin/payment/payment-write";
    }
    @PostMapping("/comm/checkRole")
    public String checkRole(@AuthenticationPrincipal MyUserDetails userDetails){
        if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return "admin/main";
        }else{
            return "member/main";
        }

    }
    // 점포 리스트페이지
    @GetMapping("/admin/store")
    private String store() {
    	return "admin/store/store";
    }
    // 점포추가 작성 페이지
    @GetMapping("/admin/store-detail")
    private String storedetail() {
    	return "/admin/store/store-detail";
    }
}
