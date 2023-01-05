package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    //임시, 사원페이지로 이동
    @GetMapping("/member/main")
    public String member(){
        return "member/main";
    }

    //임시, 어드민페이지로 이동
    @GetMapping("/admin/main")
    public String admin(){
        return "admin/main";
    }
    
    //일 근태현황 페이지
    @GetMapping("/admin/day_attendance")
    public String dayAttendance() {
    	return "admin/attendance/day_attendance";
    }
    
    //월 근태현황 페이지
    @GetMapping("/admin/month_attendance")
    public String monthAttendance() {
    	return "admin/attendance/month_attendance";
    }
}
