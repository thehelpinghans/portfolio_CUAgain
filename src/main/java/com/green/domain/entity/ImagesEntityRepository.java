package com.green.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagesEntityRepository extends JpaRepository<ImagesEntity,Long> {

    Optional<ImagesEntity> findByNewName(String s);
}
