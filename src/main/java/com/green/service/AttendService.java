package com.green.service;

import java.util.Optional;

import com.green.domain.dto.AttendanceInsertDTO;
import com.green.domain.dto.AttendanceUpdateDTO;
import com.green.domain.entity.AttendanceEntity;

public interface AttendService {

	void save(long id, AttendanceInsertDTO dto, AttendanceUpdateDTO udto);

	Optional<AttendanceEntity> findByOnTime(long id);

}
