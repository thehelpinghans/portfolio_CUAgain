package com.green.domain.dto;

import com.green.domain.entity.AddressEntity;
import com.green.domain.entity.EmployeesEntity;
import lombok.Data;

@Data
public class EmployeesListDTO {

    //사원정보 처리 필드
    private long id;
    private String email;
    private String pass;
    private String name;
    private String phone;
    private String hireDate;
    private String department;
    private String position;
    private String team;
    //주소 처리 필드
    private String address;

    public EmployeesListDTO(EmployeesEntity e){
        id=e.getId();
        email=e.getEmail();
        pass=e.getPass();
        name=e.getName();
        phone=e.getPhone();
        hireDate= String.valueOf(e.getHireDate());
        department=e.getDepartment().getName();
        position=e.getPosition().getPosition();
        team=e.getTeam().getName();
    }
    public EmployeesListDTO address(AddressEntity e){
        address="("+e.getPostcode()+") "+e.getRoadAddress()+" "+e.getJibunAddress()
                +" "+e.getDetailAddress()+" "+e.getExtraAddress();
        return this;
    }
}
