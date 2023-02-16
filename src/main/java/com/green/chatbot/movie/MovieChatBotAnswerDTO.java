package com.green.chatbot.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieChatBotAnswerDTO {
	private String today;
	private String time;
	private String scenario;
	private String repNationCd;
	private String movieName;
	private int order;
	@Builder.Default
	private String info="안녕하세요 무비안내봇입니다.</br>안내에따라 입력또는 선택하여주세요";
	
}
