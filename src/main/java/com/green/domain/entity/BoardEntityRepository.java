package com.green.domain.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByType(BoardType 자유게시판);
    
    List<BoardEntity> findByTitleContaining(String data);

    List<BoardEntity> findByWriterNameContaining(String data);

    Page<BoardEntity> findByType(BoardType type, Pageable pageable);

    List<BoardEntity> findByTitleContainingAndType(String data, BoardType boardTypeEnum);

    List<BoardEntity> findByWriterNameContainingAndType(String data, BoardType boardTypeEnum);

    Optional<BoardEntity> findByIdAndType(Long valueOf, BoardType boardTypeEnum);
}
