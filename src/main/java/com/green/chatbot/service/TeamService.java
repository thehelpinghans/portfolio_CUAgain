package com.green.chatbot.service;

import org.springframework.ui.Model;

import com.green.domain.dto.TeamDTO;

public interface TeamService {


	void save(TeamDTO dto, long dep_id);

	void getTeamListOfDep(long depId, Model model);

	void teamDelete(long id);

	String teamUpdate(long teamId, String teamName);

	void getTeamEmpList(long id, Model model);



}