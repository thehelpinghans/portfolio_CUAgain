package com.green.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryEntityRepository extends JpaRepository<SalaryEntity, Long>{
	

}
