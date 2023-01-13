package com.green.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.service.PaymentService;
import com.green.domain.dto.PaymentDTO;


@Controller
public class DocumentController {
	
	@Autowired
	PaymentService service;



	//결재 등록 페이지이동
	@GetMapping("/member/doc/reg")
	private String payMentWrite() {
		return "admin/document/docReg";
	}

	//결재 리스트 페이지이동
	@GetMapping("/member/doc/list")
	public String doclist(Model model) {
		service.getlist(model);
		return "admin/document/docList";
	}

	//결재 등록시 페이지이동
	@PostMapping("/member/docReg")
	public String docReg(PaymentDTO dto) {
		service.save(dto);

		return "redirect:/member/doc/list";
	}
	
}