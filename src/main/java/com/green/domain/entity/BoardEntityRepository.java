package com.green.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByType(BoardType 자유게시판);
    
    List<BoardEntity> findByTitleContaining(String data);

    List<BoardEntity> findByWriterNameContaining(String data);
}
