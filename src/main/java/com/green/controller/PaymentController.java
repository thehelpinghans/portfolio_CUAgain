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
public class PaymentController {
	
	@Autowired
	PaymentService service;
	
	@GetMapping("admin/payment")
	public String paymentlist(Model model) {
		service.getlist(model);
		return "admin/payment/payment";
	}
	
	@PostMapping("/member/paymant-write")
	public String paymentsave(PaymentDTO dto) {
		service.save(dto);
		return "admin/payment/payment";
	}
	
}