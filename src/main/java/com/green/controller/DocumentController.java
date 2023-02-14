package com.green.controller;

import com.green.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.chatbot.service.DocumentService;
import com.green.domain.dto.DocumentInsertDTO;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DocumentController {
	
	@Autowired
	DocumentService docService;



	//결재 등록 페이지이동
	@GetMapping("/member/doc/reg")
	private String docWrite(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
		model.addAttribute("user",userDetails);
		return "admin/document/docReg";
	}

	//결재 리스트 페이지이동
	@GetMapping("/member/doc/list")
	public String doclist(Model model) {
		docService.getList(model);
		return "admin/document/docList";
	}
	//모든문서 버튼 눌렀을 시
	@GetMapping("/member/doc/AllList")
	public String docListAll(Model model){
		docService.getAllList(model);
		return "admin/document/searchResult";
	}
	//내 문서 버튼 눌렀을 시
	@GetMapping("/member/doc/myList")
	public String docListMine(Model model, @AuthenticationPrincipal MyUserDetails userDetails){
		docService.getMyList(model, userDetails);
		return "admin/document/searchResult";
	}
	//결재문서 검색시 처리
	@GetMapping("/member/doc/searchList/{type}/{data}")
	public String docListBySearch(@PathVariable String type, @PathVariable String data, Model model){
		docService.getDocListBySearch(type,data,model);
		return "admin/document/searchResult";
	}

	//결재자가 문서상세내용 조회하면 확인상태 변경
	@ResponseBody
	@GetMapping("/member/doc/checkStatus/{docId}")
	public void checkedStatus(@PathVariable long docId){
		docService.checkedStatus(docId);
	}
	//결재자가 승인(1)/ 반려(2) 버튼 눌렀을 때
	@ResponseBody
	@GetMapping("/member/doc/docStatus/{docId}/{docStatus}")
	public void checkedStatus(@PathVariable long docId, @PathVariable long docStatus){
		docService.changeDocStatus(docId, docStatus);
	}
	//제목 눌렀을 때 결재 디테일 /member/doc/detailTag/${docId}
	@GetMapping("/member/doc/detailTag/{docId}")
	public String docDetail(@PathVariable long docId, Model model, @AuthenticationPrincipal MyUserDetails userDetails){
		docService.getDetail(docId,userDetails.getId(),model);
		return "admin/document/docDetailTag";
	}
	//수정 버튼 눌렀을 때 수정페이지
	@GetMapping("/member/doc/editTag/{docId}")
	public String docedit(@PathVariable long docId, Model model){
		docService.getEditTag(docId,model);
		return "admin/document/docEditTag";
	}
	//수정 완료 눌렀을 때 수정완료처리
	@ResponseBody
	@PostMapping("/member/doc/edit")
	public void editDone(long docId, String title, String content){
		docService.editProcess(docId, title,content );
	}

	//결재 등록시 페이지이동
	@PostMapping("/member/docReg")
	public String docReg(DocumentInsertDTO dto) {
		docService.save(dto);
		return "redirect:/member/doc/list";
	}
	
}