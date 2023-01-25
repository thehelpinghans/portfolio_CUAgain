package com.green.domain.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreEntityRepository extends JpaRepository<StoreEntity, Long>{


	Page<StoreEntity> findByNameContaining(String name, Pageable pageable);

	Page<StoreEntity> findByManagerNameContaining(String name, Pageable pageable);

}
