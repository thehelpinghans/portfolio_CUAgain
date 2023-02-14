package com.green.chatbot.service;

import org.springframework.ui.Model;

import com.green.domain.dto.StoreListDTO;
import com.green.domain.dto.StoreSaveDTO;

public interface StoreService {

	void save(StoreSaveDTO dto);

	void getlist(Model model, int page);

	void detail(long id, Model model);

	void delete(long id);

	void update(StoreSaveDTO dto, long id, long employeeId);

	void search(String name, String type, Model model, int page);


}
