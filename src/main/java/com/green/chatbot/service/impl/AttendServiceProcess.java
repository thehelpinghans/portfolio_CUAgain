package com.green.chatbot.service.impl;

import java.security.Principal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.green.chatbot.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.domain.dto.AdminAttendanceListDTO;
import com.green.domain.dto.AttendanceCalendarDTO;
import com.green.domain.dto.AttendanceDetailDTO;
import com.green.domain.dto.AttendanceInsertDTO;
import com.green.domain.dto.AttendanceListDTO;
import com.green.domain.dto.AttendanceListRequestDTO;
import com.green.domain.dto.DepartmentStringDTO;
import com.green.domain.dto.EmployeesListDTO;
import com.green.domain.entity.AttendEntityRepository;
import com.green.domain.entity.AttendanceEntity;
import com.green.domain.entity.DepartmentEntity;
import com.green.domain.entity.DepartmentEntityRepository;
import com.green.domain.entity.EmployeesEntity;
import com.green.domain.entity.EmployeesEntityRepository;

@Service
public class AttendServiceProcess implements AttendService {
	
	@Autowired
	private AttendEntityRepository attendRepo;
	
	@Autowired
	private EmployeesEntityRepository empRepo;
	
	@Autowired
	private DepartmentEntityRepository departRepo;
	
	//@Transactional
	@Override
	public String save(long id) {
		//LocalDate date=findByOnTime(id).get().getDate();
		//System.out.println(date);
		LocalDate now = LocalDate.now();
		Optional<AttendanceEntity> attenEntity=findByOnTime(id, now);
		
		// 이미 오늘 기록이 있을 경우
		if(attenEntity.isPresent()) {		
			return "이미 출근 처리가 되었습니다.";

		}else {
			
			Date date = new Date();
			AttendanceInsertDTO dto=new AttendanceInsertDTO();
			dto.setDate(now);
			dto.setInTime(date);
			//dto.setAttendStatus("출근");
			
			attendRepo.save(dto.toEntity()
					.employeeId(empRepo.findById(id).orElseThrow())
					);//저장
			return "출근 처리가 되었습니다.";
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
		Optional<AttendanceEntity> last2=attendRepo.findFirstByEmployee_idAndAttendStatusOrderByInTimeDesc(id, "조퇴");
		Optional<AttendanceEntity> last3=attendRepo.findFirstByEmployee_idAndAttendStatusOrderByInTimeDesc(id, "퇴근");
		// 만약 마지막 출근 기록이 존재하면
		if (last.isPresent()) { 
			//퇴근 등록 처음했을 때 "퇴근or조퇴 처리가 되었습니다."
			return outCheck(last).getAttendStatus()+" 처리가 되었습니다.";
		} else if(last2.isPresent()) {
			if(outCheck(last2).getAttendStatus()=="조퇴") {
				return "이미 조퇴처리가 되었습니다.";
			}
			return outCheck(last2).getAttendStatus()+" 처리가 되었습니다.";
		} else if(last3.isPresent()) {
			if(outCheck(last3).getAttendStatus()=="퇴근") {
				return "이미 퇴근처리가 되었습니다.";
			}
			return outCheck(last3).getAttendStatus()+" 처리가 되었습니다.";
		} else {
			// 마지막 기록이 없으면 출근을 하라고 해야 됨
			return "출근등록이 되어 있지 않습니다.";
		}

	}
	
	//조퇴, 퇴근 등록 편의메서드
	@Override
	public AttendanceEntity outCheck(Optional<AttendanceEntity> last) {
		Date outTime=new Date();
		AttendanceEntity lastEntity=last.get();
		lastEntity.setOutTime(outTime);
		// 조퇴, 퇴근 체크
		lastEntity.checkStatus();
		AttendanceEntity nowAttendance = attendRepo.save(lastEntity);//저장
		return nowAttendance;
	}
	
	//마이 근태 리스트
	@Override
	public void attedList(long id, Model model) {
		model.addAttribute("list", attendRepo.findTop5ByEmployee_idOrderByDateDesc(id).stream().map(AttendanceListDTO::new).collect(Collectors.toList()));
		model.addAttribute("employee", empRepo.findById(id).stream().map(EmployeesListDTO::new).collect(Collectors.toList()));
		
	}
	//마이근태현황페이지
	@Override
	public List<AttendanceListDTO> getList(long id, Pageable pageable, AttendanceListRequestDTO dto) {
		Page<AttendanceEntity> result= attendRepo.findByEmployee_idAndDateBetweenOrderByDateDesc(id, dto.getStart(), dto.getEnd(), pageable);
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
	//관리자 근태현황 들어갔을 때
	@Override
	public void adminList(Model model, AdminAttendanceListDTO dto, int page) {
	
		int size=5;
		

		Sort sort=Sort.by(Direction.DESC, "employeeId");
		Pageable pageable=PageRequest.of(page-1, size, sort);
		System.err.println("레포지토리 실행작동");
		Page<AttendanceEntity> result=attendRepo.findAll(pageable);
		int nowPage = result.getNumber()+1;
		int startPage = Math.max(nowPage -4, 1);
		int endPage = Math.min(nowPage +5, result.getTotalPages());
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("p", result);

		model.addAttribute("list", result.stream().map(AdminAttendanceListDTO::new).collect(Collectors.toList()));
	}
	
	
	//관리자 페이지 근태검색
	@Transactional
	@Override
	public void search(String keyword, String department, String start, String end, Model model, @RequestParam(defaultValue = "1")int page) {
		System.err.println("실행작동");
		
		System.out.println("keyword: " + keyword);
		
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.println(formatter);
		//DateTimeFormatter formatter2=DateTimeFormatter.ofPattern(end);
		System.out.println(start);
		LocalDate startDate=LocalDate.parse(start, formatter);
		LocalDate endDate=LocalDate.parse(end, formatter);
		//LocalDate.parse(end, formatter2);
		
		int size=5;

		Sort sort=Sort.by(Direction.DESC, "employeeId");
		Pageable pageable=PageRequest.of(page-1, size, sort);
		Page<AttendanceEntity> result=attendRepo.findAllByEmployeeNameContainingAndEmployeeDepNameContainingAndDateBetweenOrderByDateDesc(keyword,department,startDate,endDate, pageable);
		System.err.println("정상실행");
		int nowPage = result.getNumber()+1;
		int startPage = Math.max(nowPage -4, 1);
		int endPage = Math.min(nowPage +5, result.getTotalPages());
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		System.err.println(result.hasPrevious());
		model.addAttribute("p",result);//.stream().map(entity->entity.toListDTO2()).collect(Collectors.toList());
		model.addAttribute("department", department);
		model.addAttribute("keyword", keyword);
		
		
		System.err.println(result.map(AdminAttendanceListDTO::new));
		
		model.addAttribute("list", result.map(AdminAttendanceListDTO::new));
		System.err.println("start : "+start);
		System.err.println("end : "+end);

	}
	
	//풀캘린더
	@Override
    public List<AttendanceDetailDTO> getAttendance(AttendanceCalendarDTO dto){
        LocalDate date = dto.getMonth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        YearMonth month = YearMonth.from(date);
        LocalDate start = month.atDay(1);
        LocalDate end = month.atEndOfMonth();
        List<AttendanceEntity> attendance;
        if (dto.getDepartmentId() == null) { // 전체 검색일 경우 넘어오는 데이터 없음
            attendance = attendRepo.findAllByDateBetween(start, end);
        } else {
            attendance = attendRepo.findAllByDateBetweenAndEmployeeDepId(start, end, dto.getDepartmentId());
            System.out.println("?");
        }
        List<AttendanceDetailDTO> attendanceDetailDTOs = new ArrayList<AttendanceDetailDTO>();
        for (AttendanceEntity entity : attendance) {
            attendanceDetailDTOs.add(entity.toAttendDetailDTO());
        }
        return attendanceDetailDTOs;
    }
    @Override
    public void getDepartmentList(Model model) {
        // TODO Auto-generated method stub
        List<DepartmentEntity> entities = departRepo.findAll();
        List<DepartmentStringDTO> dto = new ArrayList<DepartmentStringDTO>();
        for (DepartmentEntity entity : entities) {
            dto.add(entity.toDepartmentStringDTO());
        }
        model.addAttribute("departments", dto);
    }

	

}
