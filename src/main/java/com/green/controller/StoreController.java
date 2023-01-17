package com.green.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.service.StoreService;
import com.green.service.impl.StoreServiceProcess;
import com.green.domain.dto.StoreListDTO;
import com.green.domain.dto.StoreSaveDTO;
import com.green.security.MyUserDetails;

@Controller
public class StoreController {
	
	@Autowired
	StoreService service;
	
	@GetMapping("/admin/store")
	public String getlist(Model model) {
		System.out.println("eeeeeeeeeeeeeeeeeeee");
		service.getlist(model);
		return "/admin/store/store";
	}
	
	@PostMapping("/admin/store-write")
	public String savestore(StoreSaveDTO dto) {
		System.err.println("controller");
		service.save(dto);
		return "/admin/store/store";
	}
}
