package com.green.domain.dto;

import lombok.Data;

@Data
public class EmployeesListDTO {

    //사원정보 처리 필드
    private String email;
    private String pass;
    private String name;
    private String phone;
    private String hireDate;
    private String department;
    private String position;

    //주소 처리 필드
    private String postcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String extraAddress;

}
