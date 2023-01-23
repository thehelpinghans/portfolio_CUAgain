package com.green.domain.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.green.domain.dto.AdminAttendanceListDTO;

@Repository
public interface AttendEntityRepository extends JpaRepository<AttendanceEntity, Long>{

	List<AttendanceEntity> findAllByEmployee_id(long id);


	Optional<AttendanceEntity> findFirstByEmployee_idAndAttendStatusOrderByInTimeDesc(Long id, String status);


	List<AttendanceEntity> findTop5ByEmployee_idOrderByDateDesc(long id);


	Page<AttendanceEntity> findByEmployee_idAndDateBetweenOrderByDateDesc(long id, LocalDate start, LocalDate end,
			Pageable pageable);

	List<AttendanceEntity> findAllByEmployee_name(String name);
	

	Page<AttendanceEntity> findAll(Pageable pageable);

	//List<AttendanceEntity> findAllByDateContainingAndEmployeeNameContainingAndEmployeeDepNameContaining(LocalDate date, String keyword, String department);

	Page<AttendanceEntity> findAllByEmployeeNameContainingAndEmployeeDepNameContainingAndDateBetweenOrderByDateDesc(String keyword,
			String department, LocalDate startDate, LocalDate endDate, Pageable pageable);

//List<AttendanceEntity> findAllByEmployeeDepNameContaining(String keyword);
//	@Query("SELECT * FROM attendance a join employees b on a.employee_id=b.employee_id where b.employee_name LIKE %:keyword%")
//	List<AttendanceEntity> searchAllByEmployee_nameLike(@Param("keyword") String keyword);


	
}
