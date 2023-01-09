package com.green.controller;

import com.green.domain.dto.EmployeesInsertDTO;
import com.green.domain.entity.Position;
import com.green.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

	@Autowired
	EmployeesService employeesService;

	
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
    public String empReg(Model model){

		List<Position> positionList = List.of(Position.values());
		//System.out.println(list);
		//List<String> departmentList;
		//List<String> teamList;
		model.addAttribute("positionList", positionList);
        return "/admin/employee/reg";
    }
    //사원 조회/수정 페이지이동
    @GetMapping("/admin/emp/list")
    public String empList(Model model){

		employeesService.getEmpList(model);
        return "/admin/employee/list";
    }

    //사원디테일가져오기
    @GetMapping("/admin/member/detailTag")
    public String empDetail(){
        return "admin/employee/detailTag";
    }

	//사원 증명사진 이미지 temp 저장
	@ResponseBody//응답데이터를 json타입으로 리턴합니다.
	@PostMapping("/admin/temp-upload")
	public Map<String, String> tempUpload(MultipartFile gimg) {
		return employeesService.fileTempUpload(gimg);
	}

	//사원 등록시
	@PostMapping("/admin/emp/reg")
	public String adminGoodsUpload(EmployeesInsertDTO dto) {
		//등록하면 dto에 업데이트! 하고 사원 리스트로 리턴
		System.out.println(dto);
		employeesService.save(dto);
		return "redirect:/admin/emp/list";	//사원리스트 페이지로 이동
	}
}
