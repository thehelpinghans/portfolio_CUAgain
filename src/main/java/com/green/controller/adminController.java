package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminController {

    //사원 등록 페이지이동
    @GetMapping("/admin/emp/reg")
    public String empReg(){
        return "/admin/employee/reg";
    }
    //사원 조회/수정 페이지이동
    @GetMapping("/admin/emp/list")
    public String empList(){
        return "/admin/employee/list";
    }

    //사원디테일가져오기
    @GetMapping("/admin/member/detailTag")
    public String empDetail(){
        return "admin/employee/detailTag";
    }
}
