package com.green.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

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

	

}
