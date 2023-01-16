package com.green.domain.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendEntityRepository extends JpaRepository<AttendanceEntity, Long>{

	List<AttendanceEntity> findAllByEmployee_id(long id);


	Optional<AttendanceEntity> findFirstByEmployee_idAndAttendStatusOrderByInTimeDesc(Long id, String status);


	List<AttendanceEntity> findTop5ByEmployee_idOrderByDateDesc(long id);


	Page<AttendanceEntity> findByEmployee_idAndDateBetweenOrderByDateDesc(long id, LocalDate start, LocalDate end,
			Pageable pageable);



}
