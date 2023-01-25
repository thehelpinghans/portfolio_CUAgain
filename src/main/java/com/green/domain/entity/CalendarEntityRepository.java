package com.green.domain.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CalendarEntityRepository extends JpaRepository<CalendarEntity, Long>{
    List<CalendarEntity> findAllByStartBeforeOrEndAfterAndEmployeeId(LocalDate end, LocalDate start, long employeeId);
    List<CalendarEntity> findAllByStartBeforeOrEndAfterAndEmployeeId(Date end, Date start, long employeeId);
}