package com.green.service;

import org.springframework.ui.Model;

import com.green.domain.dto.PaymentDTO;
import com.green.security.MyUserDetails;

public interface PaymentService {

	void getlist(Model model);

	void save(PaymentDTO dto);

}
