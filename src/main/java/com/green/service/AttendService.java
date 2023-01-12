package com.green.service;

import com.green.domain.dto.AttendanceInsertDTO;

public interface AttendService {

	void save(long id, AttendanceInsertDTO dto);

}
