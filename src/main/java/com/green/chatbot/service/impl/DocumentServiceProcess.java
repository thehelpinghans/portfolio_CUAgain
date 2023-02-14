package com.green.chatbot.service.impl;

import javax.transaction.Transactional;

import com.green.chatbot.service.DocumentService;
import com.green.domain.dto.DocumentListDTO;
import com.green.security.MyUserDetails;
import com.green.utils.DocuCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.DocumentInsertDTO;
import com.green.domain.entity.EmployeesEntityRepository;
import com.green.domain.entity.DocumentEntity;
import com.green.domain.entity.DocumentEntityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceProcess implements DocumentService {
	
	@Autowired
	DocumentEntityRepository docRepo;
	
	@Autowired
	EmployeesEntityRepository empRepo;


	//결재자가 문서확인했을때
	@Transactional
	@Override
	public void checkedStatus(long docId) {
		docRepo.findById(docId).orElseThrow().setCheckStatus(true);
	}
	//결재자가 승인(1) / 반려(2) 했을때


	@Transactional
	@Override
	public void changeDocStatus(long docId, long docStatus) {
		docRepo.findById(docId).orElseThrow().setDocStatus(docStatus).setApprovalDate();
	}

	@Override
	public void getList(Model model) {
		model.addAttribute("list", docRepo.findAllByOrderByCreatedDateDesc().stream()
				.map(DocumentListDTO::new)
				.collect(Collectors.toList()));
	}

	//결재문서 등록시 처리
	@Transactional
	@Override
	public void save(DocumentInsertDTO dto) {
		DocumentEntity doc = docRepo.save(DocumentEntity.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(empRepo.findById(dto.getWriterId()).orElseThrow())
				.acceptor(empRepo.findById(dto.getAcceptorId()).orElseThrow())
				.build());
	}

	//모든문서 버튼 눌렀을 때
	@Override
	public void getAllList(Model model) {
		List<DocumentListDTO> list = docRepo.findAllByOrderByCreatedDateDesc().stream()
				.map(DocumentListDTO::new)
				.collect(Collectors.toList());
		model.addAttribute("list",list);
	}

	//내 문서 버튼 눌렀을 시 처리
	@Override
	public void getMyList(Model model, MyUserDetails userDetails) {
		List<DocumentListDTO> list = docRepo.findByWriterId(userDetails.getId()).stream()
				.map(DocumentListDTO::new)
				.collect(Collectors.toList());
		List<DocumentListDTO> list2 = docRepo.findByAcceptorId(userDetails.getId()).stream()
				.map(DocumentListDTO::new)
				.collect(Collectors.toList());
		list.addAll(list2);

		DocuCompare comp = new DocuCompare();
		list.sort(comp);

		model.addAttribute("list",list );
	}

	//결재문서 디테일
	@Override
	public void getDetail(long docId, long userId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("dto", docRepo.findById(docId).map(DocumentListDTO::new).orElseThrow());
	}

	@Override
	public void getEditTag(long docId, Model model) {
		model.addAttribute("dto", docRepo.findById(docId).map(DocumentListDTO::new).orElseThrow());
	}

	@Transactional
	@Override
	public void editProcess(long docId, String title, String content) {
		docRepo.findById(docId).orElseThrow().update(title,content);
	}

	@Override
	public void getDocListBySearch(String type, String data, Model model) {
		if(type.equals("Name")){
			List<DocumentListDTO> list = docRepo.findByWriterNameContaining(data).stream()
					.map(DocumentListDTO::new)
					.collect(Collectors.toList());
			List<DocumentListDTO> list2 = docRepo.findByAcceptorNameContaining(data).stream()
					.map(DocumentListDTO::new)
					.collect(Collectors.toList());

			list.addAll(list2);
			DocuCompare comp = new DocuCompare();
			list.sort(comp);
			model.addAttribute("list",list );
		} else if (type.equals("depName")) {
			List<DocumentListDTO> list = docRepo.findByWriterDepNameContaining(data).stream()
					.map(DocumentListDTO::new)
					.collect(Collectors.toList());
			model.addAttribute("list",list );
		} else{     //type.equals("docId")

			DocumentListDTO list = docRepo.findById(Long.parseLong(data))
					.map(DocumentListDTO::new).orElse(new DocumentListDTO());
			model.addAttribute("list",list );
			/*
			model.addAttribute("list",
					empRepo.findById(Long.valueOf(data))
							.map(e-> new EmployeesListDTO(e).address(e.getAddress()))
							.orElseThrow());
			*/
		}
	}
}
