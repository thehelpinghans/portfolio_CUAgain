package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminController {
	
	@GetMapping("/board/notice")
	public String notice() {
		return "/admin/board/notice";
	}
	
	@GetMapping("/board/free")
	public String free() {
		return "/admin/board/free";
	}
	
	@GetMapping("/board/write")
	public String write() {
		return "/admin/board/write";
	}
	
	@GetMapping("/board/free-write")
	public String freewrite() {
		return "/admin/board/free-write";
	}
	
	@GetMapping("/board/view")
	public String view() {
		return "/admin/board/view";
	}
	
	@GetMapping("/board/free-view")
	public String freeview() {
		return "/admin/board/free-view";
	}
	
	@GetMapping("/board/edit")
	public String edit() {
		return "/admin/board/edit";
	}
	
	@GetMapping("/board/free-edit")
	public String freeedit() {
		return "/admin/board/free-edit";
	}
	

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
