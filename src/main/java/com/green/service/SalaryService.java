package com.green.service;

import com.green.domain.dto.SalaryResponseDTO;
import com.green.domain.dto.SalarySaveDTO;
import com.green.domain.dto.SalaryUpdateDTO;
import com.green.domain.entity.SalaryEntity;

public interface SalaryService {
	
	Long register(SalarySaveDTO dto);
	
	default SalaryEntity dtoToEntity(SalarySaveDTO dto) {
		return null;
		
		
	}

	static Long save(SalarySaveDTO requestDTO) {
		
		return null;
	}

	static Long update(Long id, SalaryUpdateDTO requestDto) {
		
		return null;
	}

	static SalaryResponseDTO findById(Long id) {
		
		return null;
	}

	static void delete(Long id) {
		
		
	}
}
