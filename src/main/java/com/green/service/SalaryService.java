package com.green.service;

import com.green.domain.dto.SalaryDTO;
import com.green.domain.entity.SalaryEntity;

public interface SalaryService {
	
	Long register(SalaryDTO dto);
	
	default SalaryEntity dtoToEntity(SalaryDTO dto) {
		return null;
		
		
	}
}
