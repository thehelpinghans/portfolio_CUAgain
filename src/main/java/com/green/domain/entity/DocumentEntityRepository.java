package com.green.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface DocumentEntityRepository extends JpaRepository<DocumentEntity, Long>{

    List<DocumentEntity> findByWriterId(long writer_id);

    List<DocumentEntity> findByAcceptorId(long id);

    List<DocumentEntity> findAllByOrderByCreatedDateDesc();

    List<DocumentEntity> findByWriterIdContaining(long parseLong);

    List<DocumentEntity> findByAcceptorIdContaining(long parseLong);

    List<DocumentEntity> findByIdContaining(long parseLong);

    List<DocumentEntity> findByWriterNameContaining(String data);

    List<DocumentEntity> findByAcceptorNameContaining(String data);

    List<DocumentEntity> findByWriterDepNameContaining(String data);
}
