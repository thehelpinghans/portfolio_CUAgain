package com.green.controller;

import com.green.domain.dto.DepartmentDTO;
import com.green.domain.dto.EmployeesDetailDTO;
import com.green.domain.dto.EmployeesInsertDTO;
import com.green.domain.dto.TeamDTO;
import com.green.chatbot.service.DepartmentService;
import com.green.chatbot.service.EmployeesService;
import com.green.chatbot.service.SalaryService;
import com.green.chatbot.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
	
	@Autowired
	private TeamService teamService;

	@Autowired
	EmployeesService employeesService;
	
	@Autowired
	DepartmentService depService;

	@Autowired
	SalaryService salService;

//	@GetMapping("/board/notice")
//	public String notice() {
//		return "/admin/board/notice";
//	}
//	//자유게시판 목록
//	@GetMapping("/admin/board/free")
//	public String free() {
//		return "admin/board/free";
//	}
//
//
//
//	//자유게시판 등록페이지이동
//	@GetMapping("/admin/board/free-write")
//	public String freewrite() {
//		return "admin/board/free-write";
//	}
//
//	//공지사항 상세페이지
//	@GetMapping("/admin/board/view")
//	public String view() {
//		return "admin/board/view";
//	}
//
//	//자유게시판 상세페이지
//	@GetMapping("/admin/board/free-view")
//	public String freeview() {
//		return "admin/board/free-view";
//	}
//
//	//공지사항 수정페이지
//	@GetMapping("/admin/board/edit")
//	public String edit() {
//		return "admin/board/edit";
//	}
//
//	//자유게시판 수정페이지
//	@GetMapping("/admin/board/free-edit")
//	public String freeedit() {
//		return "admin/board/free-edit";
//	}
	

    //사원 등록 페이지이동
    @GetMapping("/admin/emp/reg")
    public String empReg(Model model){

		employeesService.getBaseInfo(model);

        return "admin/employee/reg";
    }
   
   //부서선택 되었을때 해당하는 부서 이름 리스트 가져오기
    @ResponseBody
	@GetMapping("/admin/teamList/{depName}")
	public List<String> teamListOfDep(@PathVariable String depName){
		System.out.println(depName);
		return employeesService.getTeamListOfDef(depName);
	}

    
///////////////////////////////////////////////////////////////
    
  //회사명 클릭시 부서+팀리스트 HTML 반환
    @GetMapping("/member/departments")
    public String departments(Model model){
    	depService.getList(model);
        return "admin/depart/department-list";
    }

    //회사명클릭시 모든사원리스트 HTML 반환
	@GetMapping("/member/all/empList")
	public String depAllList(Model model) {
		depService.getEmpList(model);
		return "admin/depart/depEmpList";
	}
//	//부서명클릭시 부서소속리스트 HTML 반환
//	@GetMapping("/member/DepMem/empList/{depId}")
//	public String DepMemList(@PathVariable("depId") long id, Model model) {
//		depService.getDepEmpList(id, model);
//		return "admin/depart/depEmpList";
//	}
	//팀명클릭시 팀소속리스트 HTML 반환
	@GetMapping("/member/TeamMem/empList/{TemaId}")
	public String teamMemList(@PathVariable("TemaId") long id, Model model) {
		System.err.println("오류");
		teamService.getTeamEmpList(id, model);
		return "admin/depart/depEmpList";
	}
		
    //부서등록
	@ResponseBody
	@PostMapping("/admin/addDepart")
	public void depratReg(DepartmentDTO dto) {
		System.out.println(dto);
		//등록하면 dto에 업데이트! 하고 사원 리스트로 리턴
		depService.save(dto);
		//return "redirect:/admin/department";	
	}
	//부서이름 수정 
	@ResponseBody
	@PostMapping("/admin/depUpdate/{depId}")
	public String depUpdate(@PathVariable("depId") long depId, String departmentName) {
		return depService.depUpdate(depId, departmentName);
	}
	//부서 삭제 
	@ResponseBody
	@DeleteMapping("/admin/depDelete/{depId}")
	public void depDelete(@PathVariable("depId") long id) {
		depService.depDelete(id);
	}
	//부서메뉴중 팀 등록
	@ResponseBody
	@PostMapping("/admin/teamInsert/{dep_id}")
	public void teamReg(TeamDTO dto ,@PathVariable long dep_id) {
		//등록하면 dto에 업데이트! 하고 사원 리스트로 리턴
		System.err.println("팀등록");
		teamService.save(dto, dep_id);
		//return "redirect:/admin/department";	
	}
	//팀 삭제"/admin/depDelete/"+teamId
	@ResponseBody
	@DeleteMapping("/admin/teamDelete/{teamId}")
	public void teamDelete(@PathVariable("teamId") long id) {
		teamService.teamDelete(id);
	}
	//팀이름 수정 
		@ResponseBody
		@PostMapping("/admin/teamUpdate/{teamId}")
		public String teamUpdate(@PathVariable("teamId") long teamId, String teamName) {
			
			return teamService.teamUpdate(teamId, teamName);
		}
	
//////////////////////////////////////////////////////////////////////	
    //사원 조회/수정 페이지이동
    @GetMapping("/member/emp/list")
    public String empList(Model model){
		employeesService.getEmpList(model);
        return "admin/employee/list";
    }
	//사원 검색시 처리

	@GetMapping("/admin/emp/searchList/{type}/{data}")
	public String empListBySearch(@PathVariable String type, @PathVariable String data, Model model){
		employeesService.getEmpListBySearch(type,data,model);
		return "admin/employee/searchResult";

	}
	//사원리스트 초기화시
	@GetMapping("/admin/emp/allList")
	public String empListByReset(Model model){
		employeesService.getEmpList(model);
		return "admin/employee/searchResult";

	}
    //사원디테일가져오기
    @GetMapping("/admin/member/detailTag/{memberId}")
    public String empDetail(@PathVariable long memberId, Model model){
		employeesService.getDetail(memberId,model);
        return "admin/employee/detailTag";
    }

	//사원수정태그가져오기
	@GetMapping("/admin/member/editTag/{memberId}")
	public String empedit(@PathVariable long memberId, Model model){
		employeesService.getDetail(memberId,model);
		return "admin/employee/editTag";
	}
	//사원 증명사진 이미지 temp 저장
	@ResponseBody//응답데이터를 json타입으로 리턴합니다.
	@PostMapping("/admin/temp-upload")
	public Map<String, String> tempUpload(MultipartFile gimg) {
		return employeesService.fileTempUpload(gimg);
	}

	//사원 등록시
	@PostMapping("/admin/emp/reg")
	public String empReg(EmployeesInsertDTO dto) {
		//등록하면 dto에 업데이트! 하고 사원 리스트로 리턴
		employeesService.save(dto);
		return "redirect:/member/emp/list";	//사원리스트 페이지로 이동
	}
	//사원 수정시
	@PostMapping("/admin/emp/update")
	public String empUpdate(EmployeesDetailDTO dto) {
		//등록하면 dto에 업데이트! 하고 사원 리스트로 리턴
		employeesService.update(dto);
		return "redirect:/member/emp/list";	//사원리스트 페이지로 이동
	}
	//사원 비밀번호 수정시
	@ResponseBody
	@GetMapping("/admin/emp/passUpdate")
	public void empPassUpdate(@RequestParam(name = "empId") String empId,@RequestParam(name = "pass") String pass ){
		long numEmpId = Long.parseLong(empId);
		employeesService.passUpdate(numEmpId,pass);
	}

	//사원 검색
	@GetMapping("/admin/salary/search/{empId}")
	public String empSearch(Model model, @PathVariable long empId) {
		salService.searchEmp(model, empId);
		return "admin/salary/empSearchResult";
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
