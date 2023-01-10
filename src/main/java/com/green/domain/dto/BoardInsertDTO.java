package com.green.domain.dto;

import com.green.domain.entity.BoardType;
import com.green.domain.entity.EmployeesEntity;
import lombok.Data;

@Data
public class BoardInsertDTO {

    private String title;

    private String content;

    private BoardType boardType;


}
