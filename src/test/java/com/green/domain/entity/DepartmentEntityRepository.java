package com.green.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentEntityRepository extends JpaRepository<DepartmentEntity, Long>{

}
