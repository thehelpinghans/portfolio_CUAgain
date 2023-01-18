package com.green.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamEntityRepository extends JpaRepository<TeamEntity,Long> {
    List<TeamEntity> findByDepId(long depId);

    Optional<TeamEntity> findByName(String team);

	void deleteAllByDep_id(long deptId);

	List<TeamEntity> findAllBydep(DepartmentEntity e);
}