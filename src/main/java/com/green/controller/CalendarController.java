package com.green.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.green.domain.dto.AttendanceCalendarDTO;
import com.green.domain.dto.CalendarDetailDTO;
import com.green.chatbot.service.AttendService;
import com.green.chatbot.service.CalendarService;
@Controller
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private AttendService attendService;
    
    @GetMapping
    @ResponseBody
    public List<CalendarDetailDTO> getCalendar(Principal principal, @ModelAttribute AttendanceCalendarDTO dto) {
        long employeeId = attendService.principalId(principal);
        List<CalendarDetailDTO> calendarDTO = calendarService.getCalendar(employeeId, dto.getMonth());
        return calendarDTO;
    }
    //일정 저장
    @PostMapping
    @ResponseBody
    public void setCalendar(Principal principal, @ModelAttribute CalendarDetailDTO dto) {
        System.out.println(dto);
        long employeeId = attendService.principalId(principal);
        calendarService.setCalendar(employeeId, dto);
    }
}