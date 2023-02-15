package com.green.api.bus;

import lombok.Data;

@Data
public class BusBotRequestDTO {
	
	private String message;//질문에대한 답변
	private int order;
	
	private String nx;
	private String ny;
	
	private String arsId;
	private String stationNm;

}
