package com.green.chatbot.service;

import org.springframework.ui.Model;

import com.green.domain.dto.DepartmentDTO;

public interface DepartmentService {

	void getList(Model model);

	String depUpdate(long depId , String departmentName);

	void depDelete(long depId);

	void getEmpList(Model model);

	void save(DepartmentDTO dto);

	//void getDepEmpList(long id, Model model);


}