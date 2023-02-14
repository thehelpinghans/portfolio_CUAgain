package com.green.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.chatbot.service.AttendService;
import com.green.chatbot.service.StoreService;
import com.green.domain.dto.StoreSaveDTO;

@Controller
public class StoreController {
	
	@Autowired
	StoreService service;
	
	@Autowired
	AttendService aService;
	
	@GetMapping("/admin/store")
	public String getlist(Model model ,@RequestParam(defaultValue = "1") int page) {
		System.out.println("eeeeeeeeeeeeeeeeeeee");
		service.getlist(model , page);
		return "admin/store/store";
	}
	
	@PostMapping("/admin/store-write")
	public String savestore(StoreSaveDTO dto) {
		//System.err.println("controller");
		service.save(dto);
		return "redirect:/admin/store";
	}
	
	@GetMapping("/store/store-detail/{id}")
	public String detail(@PathVariable long id, Model model) {
		service.detail(id, model);
		return "/admin/store/store-detail";
	}
	
	@DeleteMapping("/store/store-detail/{id}")
	public String delete(@PathVariable long id) {
		service.delete(id);
		return "redirect:/admin/store";
	}
	
	@PutMapping("/store/store-detail/{id}")
	public String update(StoreSaveDTO dto, @PathVariable long id,Principal principal) {
		long employeeId= aService.principalId(principal);
		service.update(dto,id,employeeId);
		return "redirect:/admin/store";
	}
	
	@GetMapping("/admin/store-search")
	public String search(String name, String type, Model model, @RequestParam(defaultValue = "1")int page) {
		service.search(name, type, model, page);
		
		return "admin/store/store";
	}
}
