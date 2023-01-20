package com.green.domain.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//Enum
@RequiredArgsConstructor //final 필드를 파라미터로 구성하는 생성자
@Getter
public enum Position {
    사장("사장",20000000),
    이사("이사",15000000),
    부장("부장",6000000),
    팀장("팀장",5000000),
    대리("대리",4000000),
    사원("사원",3000000),
    점장("점장",3000000),
    매니저("매니저",2500000),
    직원("직원",2000000);
   
    private final String position;
    private final long baseSalary;

}
