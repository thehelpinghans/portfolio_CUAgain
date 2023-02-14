package com.green.chatbot.service;

import com.green.security.MyUserDetails;
import org.springframework.ui.Model;

import com.green.domain.dto.DocumentInsertDTO;

public interface DocumentService {

	void getList(Model model);

	void save(DocumentInsertDTO dto);

	void getMyList(Model model, MyUserDetails userDetails);

	void getDetail(long docId, long name, Model model);

	void getDocListBySearch(String type, String data, Model model);

	void getAllList(Model model);

	void checkedStatus(long docId);

	void changeDocStatus(long docId, long docStatus);

	void getEditTag(long docId, Model model);

	void editProcess(long docId, String title, String content);
}
