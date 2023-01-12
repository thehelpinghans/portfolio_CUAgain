package com.green.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.domain.dto.AttendanceInsertDTO;
import com.green.domain.entity.AttendEntityRepository;
import com.green.domain.entity.AttendanceEntity;
import com.green.domain.entity.EmployeesEntityRepository;
import com.green.service.AttendService;

@Service
public class AttendServiceProcess implements AttendService {
	
	@Autowired
	private AttendEntityRepository attendRepo;
	
	@Autowired
	private EmployeesEntityRepository empRepo;
	
	//@Transactional
	@Override
	public void save(long id, AttendanceInsertDTO dto) {
		
		attendRepo.save(dto.attendanceEntity().employeeId(empRepo.findById(id)));
	}

}
