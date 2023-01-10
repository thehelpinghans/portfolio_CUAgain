package com.green.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeesEntityRepository  extends JpaRepository<EmployeesEntity,Long> {

    Optional<EmployeesEntity> findByEmail(String s);

    Optional<EmployeesEntity> findByEmailAndEnd(String username, boolean b);
}
