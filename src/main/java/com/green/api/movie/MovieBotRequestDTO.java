package com.green.api.movie;

import lombok.Data;

@Data
public class MovieBotRequestDTO {
	private String message;
	private int order;
	private String repNationCd;
	private String movieName;
}
