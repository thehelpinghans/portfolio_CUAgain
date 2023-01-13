package com.green.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentEntityRepository extends JpaRepository<DocumentEntity, Long>{

}
