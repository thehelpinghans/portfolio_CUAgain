package com.green.chatbot.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

import com.green.domain.dto.AdminAttendanceListDTO;
import com.green.domain.dto.AttendanceCalendarDTO;
import com.green.domain.dto.AttendanceDetailDTO;
import com.green.domain.dto.AttendanceListDTO;
import com.green.domain.dto.AttendanceListRequestDTO;
import com.green.domain.entity.AttendanceEntity;

public interface AttendService {

	String save(long id);

	long principalId(Principal principal);

	Optional<AttendanceEntity> findByOnTime(long eid, LocalDate now);

	String updateOut(long id);

	void attedList(long id, Model model);

	List<AttendanceListDTO> getList(long id, Pageable pageable, AttendanceListRequestDTO dto);

	AttendanceEntity outCheck(Optional<AttendanceEntity> last);

	//List<AdminAttendanceListDTO> getAdminList(Model model, AdminAttendanceListDTO dto, AttendanceListRequestDTO rdto, Pageable pageable);

	//void adminList(Model model, AdminAttendanceListDTO dto);

	void adminList(Model model, AdminAttendanceListDTO dto, int page);

	//void search(String keyword, Model model, String department, int page);


	void search(String keyword, String department, String start, String end, Model model, int page);

	List<AttendanceDetailDTO> getAttendance(AttendanceCalendarDTO dto);

	void getDepartmentList(Model model);





	
	

	

}
