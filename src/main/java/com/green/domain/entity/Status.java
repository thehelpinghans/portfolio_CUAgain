package com.green.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//Enum
@RequiredArgsConstructor    //final 필드를 파라미터로 구성하는 생성자
@Getter
public enum Status {
	근무중("근무"), 
	연차휴가("연차"), 
	외근("외근"), 
	조퇴("조퇴"),
	병가("병가"), 
	결근("결근");
	
	private final String status;
}

