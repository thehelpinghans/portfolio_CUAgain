package com.green.chatbot.service.impl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.green.chatbot.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.green.domain.dto.CalendarDetailDTO;
import com.green.domain.entity.CalendarEntity;
import com.green.domain.entity.CalendarEntityRepository;
import com.green.domain.entity.EmployeesEntity;

@Service
public class CalenderServiceProcess implements CalendarService {
    @Autowired
    private CalendarEntityRepository calendarEntityRepository;
    // 내 일정 달별로 조회
    @Override
    public List<CalendarDetailDTO> getCalendar(long employeeId, Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        YearMonth month = YearMonth.from(localDate);
        Date start = Date.from(month.atDay(1).atStartOfDay().toInstant(ZoneOffset.ofHours(9)));
        Date end = Date.from(month.atEndOfMonth().atStartOfDay().toInstant(ZoneOffset.ofHours(9)));
        List<CalendarEntity> calendarEntities = calendarEntityRepository.findAllByStartBeforeOrEndAfterAndEmployeeId(end, start, employeeId);
        System.out.println(calendarEntities);
        System.out.println(start);
        System.out.println(end);
        List<CalendarDetailDTO> calendarDetailDTOs = new ArrayList<CalendarDetailDTO>();
        for (CalendarEntity entity : calendarEntities) {
            calendarDetailDTOs.add(entity.toDTO());
        }
        return calendarDetailDTOs;
    }
    // 내 일정 추가
    @Override
    public void setCalendar(long employeeId, CalendarDetailDTO dto) {
        CalendarEntity entity = CalendarEntity
        .builder()
        .content(dto.getContent())
        .start(dto.getStart())
        .end(dto.getEnd())
        .employee(
            EmployeesEntity
            .builder()
            .id(employeeId)
            .build()
            )
        .build();
        CalendarEntity calendarEntity  = calendarEntityRepository.save(entity);
        System.out.println(calendarEntity);
    }
}
