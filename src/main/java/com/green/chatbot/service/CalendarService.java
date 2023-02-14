package com.green.chatbot.service;

import java.util.Date;
import java.util.List;

import com.green.domain.dto.CalendarDetailDTO;

public interface CalendarService {

	List<CalendarDetailDTO> getCalendar(long employeeId, Date date);

	void setCalendar(long employeeId, CalendarDetailDTO dto);

}
