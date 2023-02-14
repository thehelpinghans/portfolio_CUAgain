package com.green.controller;

import com.green.domain.dto.SalaryInsertDTO;
import com.green.security.MyUserDetails;
import com.green.chatbot.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Controller
public class SalaryController {
	@Autowired
	SalaryService service;

	// 급여관리의 등록 페이지로 이동
	@GetMapping("/admin/salary/add")
	public String salaryAdd(){
		return "admin/salary/add";
	}

	//급여등록시처리
	@PostMapping("/member/salary/reg")
	public String salReg(SalaryInsertDTO dto){
		System.out.println(dto);
		service.salReg(dto);
		return "redirect:/member/salary/list";
	}

	//급여 조회 페이지이동
	@GetMapping("/member/salary/list")
	public String salList(Model model, @AuthenticationPrincipal MyUserDetails myUserDetails){
		service.salList(model, myUserDetails.getId());
		return "admin/salary/list";
	}
	//내급여리스트조회
	@GetMapping("/member/sal/searchList")
	public String salSearchList(@AuthenticationPrincipal MyUserDetails myUserDetails, Model model){
		service.salSearchList(myUserDetails.getId(), model);
		return "admin/salary/searchList";
	}

	//기간+ 사원번호로조회
	@PostMapping("/admin/sal/searchList")
	public String salSearchList(String startDate, String endDate, long empId, Model model){
		System.out.println(startDate+" "+endDate+" "+empId);
		LocalDate sd= LocalDate.parse(startDate);
		LocalDate ed = LocalDate.parse(endDate);
		service.salSearchList(sd,ed,empId, model);
		return "admin/salary/searchList";
	}
	//삭제버튼 눌렀을때
	@ResponseBody
	@DeleteMapping("/admin/sal/delete")
	public void salDelete(long salId){
		service.salDelete(salId);
	}
}
