package com.green.domain.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.green.domain.dto.AdminAttendanceListDTO;

@Repository
public interface AttendEntityRepository extends JpaRepository<AttendanceEntity, Long>{

	List<AttendanceEntity> findAllByEmployee_id(long id);


	Optional<AttendanceEntity> findFirstByEmployee_idAndAttendStatusOrderByInTimeDesc(Long id, String status);


	List<AttendanceEntity> findTop5ByEmployee_idOrderByDateDesc(long id);


	Page<AttendanceEntity> findByEmployee_idAndDateBetweenOrderByDateDesc(long id, LocalDate start, LocalDate end,
			Pageable pageable);

	List<AttendanceEntity> findAllByEmployee_name(String string);

	List<AttendanceEntity> findAllByEmployeeNameContainingOrEmployeeDepNameContaining(String keyword, String department);
	
	List<AttendanceEntity> findAllByEmployeeNameContainingAndEmployeeDepNameContaining(String keyword, String department);
	
//List<AttendanceEntity> findAllByEmployeeDepNameContaining(String keyword);
//	@Query("SELECT * FROM attendance a join employees b on a.employee_id=b.employee_id where b.employee_name LIKE %:keyword%")
//	List<AttendanceEntity> searchAllByEmployee_nameLike(@Param("keyword") String keyword);


	
}
