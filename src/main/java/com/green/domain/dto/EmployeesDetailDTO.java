package com.green.domain.dto;

import com.green.domain.entity.EmployeesEntity;
import lombok.Data;

@Data
public class EmployeesDetailDTO {

    //증명사진처리 필드
    private String newName;
    private String orgName;
    private String url;

    //사원정보 처리 필드
    private long id;
    private String email;
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


    public EmployeesDetailDTO() {
    }

    public EmployeesDetailDTO(EmployeesEntity e) {

        this.newName = e.getImage().getNewName();
        this.orgName = e.getImage().getOrgName();
        this.url=e.getImage().getUrl();
        this.email = e.getEmail();
        this.name = e.getName();
        this.phone = e.getPhone();
        this.hireDate = String.valueOf(e.getHireDate());
        this.id=e.getId();

        this.department = e.getDep().getName();
        this.team=e.getTeam().getName();
        this.position = e.getPosition().getPosition();
        this.postcode = e.getAddress().getPostcode();
        this.roadAddress = e.getAddress().getRoadAddress();
        this.jibunAddress = e.getAddress().getJibunAddress();
        this.detailAddress = e.getAddress().getDetailAddress();
        this.extraAddress = e.getAddress().getExtraAddress();
    }
}
