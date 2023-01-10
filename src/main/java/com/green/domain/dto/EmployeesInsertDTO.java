package com.green.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeesInsertDTO {

    //증명사진처리 필드
    private String newName;
    private String orgName;

    //사원정보 처리 필드
    private String email;
    private String pass;
    private String name;
    private String phone;
    private String hireDate;
    private String department;
    private String team;
    private String position;

    //주소 처리 필드
    private String postcode;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private String extraAddress;

}
