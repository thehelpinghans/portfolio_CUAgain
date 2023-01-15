package com.green.service.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.AttendanceInsertDTO;
import com.green.domain.dto.AttendanceListDTO;
import com.green.domain.dto.AttendanceListRequestDTO;
import com.green.domain.entity.AttendEntityRepository;
import com.green.domain.entity.AttendanceEntity;
import com.green.domain.entity.EmployeesEntity;
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
	public String save(long id) {
		//LocalDate date=findByOnTime(id).get().getDate();
		//System.out.println(date);
		LocalDate now = LocalDate.now();
		Optional<AttendanceEntity> attenEntity=findByOnTime(id, now);
		
		// 이미 오늘 기록이 있을 경우
		if(attenEntity.isPresent()) {		
			return "이미 출근 체크가 되었습니다.";

		}else {
			
			Date date = new Date();
			AttendanceInsertDTO dto=new AttendanceInsertDTO();
			dto.setDate(now);
			dto.setInTime(date);
			//dto.setAttendStatus("출근");
			
			attendRepo.save(dto.toEntity()
					.employeeId(empRepo.findById(id).orElseThrow())
					);//저장
			return "출근이 정상적으로 됐습니다.";
		}
		
		
	}

	@Override
	public Optional<AttendanceEntity> findByOnTime(long eid,LocalDate now) {
		List<AttendanceEntity> entity=attendRepo.findAllByEmployee_id(eid);
		// TODO: 전체 리스트가 아닌 오늘 날짜와 일치하는 출근 기록을 불러온다.
		long id=0L;
		
		for(AttendanceEntity i : entity) {
			if(i.getDate().equals(now)) {
				//System.out.println(i.getEmployee().getId());
				id=i.getId();//오늘날짜랑 같은 at pk
			}
		}
		//System.out.println(id);
		return attendRepo.findById(id);
	}
	
	@Override
	public long principalId(Principal principal) {

		Optional<EmployeesEntity> result=empRepo.findByEmail(principal.getName());
		long id=result.get().getId();
		System.err.println(id);
		return id;
	}
	
	//퇴근 등록
	@Override
	public String updateOut(long id) {
		//맨 마지막 출근기록 찾기(status 출근인경우)
		Optional<AttendanceEntity> last=attendRepo.findFirstByEmployee_idAndAttendStatusOrderByInTimeDesc(id, "출근");
		
		// 만약 마지막 기록이 존재하면
		if (last.isPresent()) {
			Date outTime=new Date();
			AttendanceEntity lastEntity=last.get();
			lastEntity.setOutTime(outTime);
			// 조퇴, 퇴근 체크
			lastEntity.checkStatus();
			AttendanceEntity nowAttendance = attendRepo.save(lastEntity);//저장
			return nowAttendance.getAttendStatus()+"처리가 되었습니다.";
		}
		
		// 마지막 기록이 없으면 출근을 하라고 해야 됨
		
		return "출근등록은 했니?ㅋ";
	}
	
	//근태 리스트
	@Override
	public void attedList(long id, Model model) {
		model.addAttribute("list", attendRepo.findTop5ByEmployee_idOrderByDateDesc(id).stream().map(AttendanceListDTO::new).collect(Collectors.toList()));
		
	}
	//마이근태현황페이지
	@Override
	public List<AttendanceListDTO> getList(long id, Pageable pageable, AttendanceListRequestDTO dto) {
		Page<AttendanceEntity> result= attendRepo.findByEmployee_idAndDateBetweenOrderByDate(id, dto.getStart(), dto.getEnd(), pageable);
		List<AttendanceListDTO> attendanceListDTO = new ArrayList<>();
		
		int total = result.getTotalPages();
		
		System.out.println(result.getContent()+".............................");
		
		for(AttendanceEntity attendanceEntity: result.getContent()) {
			AttendanceListDTO listDto = attendanceEntity.toListDTO();
			listDto.setTotalPage(total);
			attendanceListDTO.add(listDto);
		}
		
		return attendanceListDTO;
	}
	

}
