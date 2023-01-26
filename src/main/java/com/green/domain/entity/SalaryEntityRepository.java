package com.green.domain.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryEntityRepository extends JpaRepository<SalaryEntity, Long>{


	List<SalaryEntity> findByEmployeeIdOrderByPayDayDesc(long empId);

	List<SalaryEntity> findByEmployeeIdAndPayDayBetweenOrderByPayDayDesc(long empId, LocalDate startDate, LocalDate endDate);
}
