package com.green.service;

import org.springframework.ui.Model;

import com.green.domain.dto.StoreSaveDTO;

public interface StoreService {

	void getlist(Model model);

	void save(StoreSaveDTO dto);


}
