package com.green.domain.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

//Enum
@RequiredArgsConstructor //final 필드를 파라미터로 구성하는 생성자
@Getter
public enum Position {
    사장("사장"),
    이사("이사"),
    부장("부장"),
    팀장("팀장"),
    대리("대리"),
    사원("사원"),
    점장("점장"),
    매니저("매니저"),
    직원("직원");
   
    private final String position;

}
