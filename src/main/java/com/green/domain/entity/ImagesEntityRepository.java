package com.green.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesEntityRepository extends JpaRepository<ImagesEntity,Long> {

}
