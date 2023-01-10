package com.green.domain.dto;

import com.green.domain.entity.EmployeesEntity;

public class BoardInsertDTO {

    private long id;

    private String title;

    private String content;

    private long readCount;

    private boolean pin;

    private EmployeesEntity employees;

}
