package com.green.api.bus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class BusChatBotService {
	
	String[] scenario={
			
			"1. 원하는 위치를 입력하세요.</br>추천:도로명주소</br>(동입력시 동사무소기준조회)",
			"2. 정류장을 선택해주세요",
			"-> 버스 도착 정보입니다."};

	public BusChatBotAnswerDTO scenario(BusBotRequestDTO dto) {
		
		
		
		LocalDateTime today=LocalDateTime.now();
		//DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("a H:mm");
		DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
		
		BusChatBotAnswerDTO answer=BusChatBotAnswerDTO.builder()
				.today(dateFormatter.format(today))
				.time(timeFormatter.format(today))	
				.scenario(scenario[dto.getOrder()])
				.nx(dto.getNx())
				.ny(dto.getNy())
				.areaInfo(dto.getOrder()==1?dto.getMessage():null)
				.order(dto.getOrder()+1)
				.build();
		/*
		switch(dto.getOrder()) {
		case 0:
			answer.setScenario(scenario[dto.getOrder()+1]);
			answer.setOrder(dto.getOrder()+1);
			break;
		}
		//*/
		
		return answer;
	}

}
