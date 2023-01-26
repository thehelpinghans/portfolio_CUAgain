package com.green.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeesEntityRepository  extends JpaRepository<EmployeesEntity,Long> {

    Optional<EmployeesEntity> findByEmailAndEnd(String username, boolean b);


    List<EmployeesEntity> findByNameContaining(String data);

    List<EmployeesEntity> findByDepNameContaining(String data);

    Optional<EmployeesEntity> findByImageNewName(String s);

    Optional<EmployeesEntity> findByEmail(String name);

	Optional<EmployeesEntity> findByName(String name);
}
