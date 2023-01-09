package com.green.domain.entity;


import lombok.Getter;

//Enum
//@RequiredArgsConstructor    //final 필드를 파라미터로 구성하는 생성자
@Getter
public enum AttendStatus {
	근무, 
	연차휴가, 
	외근, 
	조퇴,
	병가, 
	결근
	//private final String status;
}

