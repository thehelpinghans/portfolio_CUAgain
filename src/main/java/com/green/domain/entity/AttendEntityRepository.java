package com.green.domain.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendEntityRepository extends JpaRepository<AttendanceEntity, Long>{

	List<AttendanceEntity> findAllByEmployee_id(long id);

}
