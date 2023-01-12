package com.green.controller;

import com.green.domain.dto.DepartmentDTO;
import com.green.domain.dto.EmployeesInsertDTO;
import com.green.domain.dto.TeamaddtDTO;
import com.green.domain.entity.Position;
import com.green.service.DepartmentService;
import com.green.service.EmployeesService;
import com.green.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
	
	@Autowired
	private TeamService teamService;

	@Autowired
	EmployeesService employeesService;
	
	@Autowired
	DepartmentService depServise;
	
	@GetMapping("/board/notice")
	public String notice() {
		return "/admin/board/notice";
	}
	//자유게시판 목록
	@GetMapping("/admin/board/free")
	public String free() {
		return "admin/board/free";
	}

	//공지사항 등록페이지이동
	@GetMapping("/admin/board/write")
	public String write() {
		return "admin/board/write";
	}

	//자유게시판 등록페이지이동
	@GetMapping("/admin/board/free-write")
	public String freewrite() {
		return "admin/board/free-write";
	}

	//공지사항 상세페이지
	@GetMapping("/admin/board/view")
	public String view() {
		return "admin/board/view";
	}

	//자유게시판 상세페이지
	@GetMapping("/admin/board/free-view")
	public String freeview() {
		return "admin/board/free-view";
	}

	//공지사항 수정페이지
	@GetMapping("/admin/board/edit")
	public String edit() {
		return "admin/board/edit";
	}

	//자유게시판 수정페이지
	@GetMapping("/admin/board/free-edit")
	public String freeedit() {
		return "admin/board/free-edit";
	}
	

    //사원 등록 페이지이동
    @GetMapping("/admin/emp/reg")
    public String empReg(Model model){

		employeesService.getBaseInfo(model);

        return "admin/employee/reg";
    }
    //부서등록 
    
    
	//부서선택 되었을때 해당하는 팀 이름 리스트 가져오기
	@ResponseBody
	@GetMapping("/admin/teamList/{depName}")
	public List<String> teamListOfDep(@PathVariable String depName){
		System.out.println(depName);
		return employeesService.getTeamListOfDef(depName);
	}
	//부서이름 수정 
	@ResponseBody
	@PostMapping("/admin/depUpdate/{depId}")
	public String depUpdate(@PathVariable("depId") long depId, String departmentName) {
		
		return depServise.depUpdate(depId, departmentName);
	}
	//부서 삭제 
	@DeleteMapping("/admin/depDelete/{depId}")
	public String depDelete(@PathVariable("id") long id) {
		depServise.depDelete(id);
		return "redirect:/admin/department";
	}
	//부서메뉴중 팀 추가
//	@ResponseBody
//	@PostMapping("/admin/teamInsert/{depId}")
//	public String teamInsert(@PathVariable("depId") long depId, TeamaddtDTO dto) {
//		
//		return teamService.save(depId, departmentName);
//	}
    //사원 조회/수정 페이지이동
    @GetMapping("/admin/emp/list")
    public String empList(Model model){
		employeesService.getEmpList(model);
        return "admin/employee/list";
    }

    //사원디테일가져오기
    @GetMapping("/admin/member/detailTag/{memberId}")
    public String empDetail(@PathVariable long memberId, Model model){
		employeesService.getDetail(memberId,model);
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

//    //팀등록
//    @PostMapping("/admin/teamAdd")
//    public String teamadd(TeamAddDTO dto, Model model) {
//    	teamService.save(dto, model);
//    	return "admin/depart/department";
//    } 
    //이런식으로해야하나? 
//    @PostMapping("/admin/teamAdd")
//	public String teamadd(TeamAddDTO dto, Model model) {
//    	teamService.updateMember(dto, model);
//		return "redirect:/admin/depart/department";
//	}
    
}
