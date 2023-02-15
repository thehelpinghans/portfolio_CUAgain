package com.green.api.bus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusChatBotAnswerDTO {
	
	private String today;
	private String time;
	private String scenario;
	private int order;
	
	private String nx;//위도
	private String ny;//경도
	private String areaInfo;//장소
	private String arsId;//정류소ID
	private String stationNm;//정류소이름
	
	private String finalMsg;
	
	@Builder.Default
	private String info="안녕하세요</br>CUAgain 버스챗봇입니다.";

}
