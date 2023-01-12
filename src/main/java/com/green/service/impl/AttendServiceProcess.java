package com.green.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.domain.dto.AttendanceInsertDTO;
import com.green.domain.dto.AttendanceUpdateDTO;
import com.green.domain.entity.AttendEntityRepository;
import com.green.domain.entity.AttendStatus;
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
	public void save(long id, AttendanceInsertDTO dto, AttendanceUpdateDTO udto) {
		//LocalDate date=findByOnTime(id).get().getDate();
		//System.out.println(date);
		
		Optional<AttendanceEntity> fot=findByOnTime(id);
		if(fot.isPresent()) {
			//attendRepo.save(udto.attendanceEntity().employeeId(empRepo.findById(id)));//수정
			AttendanceEntity dd=fot.get();
			dd.update(udto);
			attendRepo.save(dd);
			System.out.println(attendRepo.save(dd).getOutTime());
		}else {
			attendRepo.save(dto.attendanceEntity().employeeId(empRepo.findById(id)).addStatus(AttendStatus.출근));//저장
		}
		
	}

	@Override
	public Optional<AttendanceEntity> findByOnTime(long eid) {
		List<AttendanceEntity> entity=attendRepo.findAllByEmployee_id(eid);
		
		LocalDate date=LocalDate.now();
		long id=0L;
		
		for(AttendanceEntity i : entity) {
			if(i.getDate().equals(date)) {
				//System.out.println(i.getEmployee().getId());
				id=i.getId();//오늘날짜랑 같은 at pk
			}
		}
		//System.out.println(id);
		return attendRepo.findById(id);
	}
	


}
