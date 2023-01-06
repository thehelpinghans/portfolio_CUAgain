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
	
}
