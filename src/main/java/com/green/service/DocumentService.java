package com.green.service;

import org.springframework.ui.Model;

import com.green.domain.dto.PaymentDTO;

public interface DocumentService {

	void getlist(Model model);

	void save(PaymentDTO dto);

}
