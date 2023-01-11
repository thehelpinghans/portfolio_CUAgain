package com.green.service;

import org.springframework.ui.Model;

import com.green.domain.dto.DepartmentDTO;

public interface DepartmentService {

	void getList(Model model);

	String depUpdate(long depId , String departmentName);

	void depDelete(long depId);


}
