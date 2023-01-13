package com.green.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.service.PaymentService;
import com.green.domain.dto.PaymentDTO;
import com.green.security.MyUserDetails;


@Controller
public class DocumentController {
	
	@Autowired
	PaymentService service;
	
	@GetMapping("/member/payment")
	public String doclist(Model model) {
		service.getlist(model);
		return "admin/document/docList";
	}
	
	@PostMapping("/member/paymant-write")
	public String docReg(PaymentDTO dto) {
		service.save(dto);

		return "admin/document/docReg";
	}
	
}