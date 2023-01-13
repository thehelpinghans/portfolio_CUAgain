package com.green.domain.dto;

import com.green.domain.entity.BoardEntity;
import com.green.domain.entity.BoardType;
import com.green.domain.entity.EmployeesEntity;
import lombok.Data;

@Data
public class BoardInsertDTO {

    private String title;

    private String content;

    private String type;


//    public BoardEntity toBoardEntity(){
//
//        return BoardEntity.builder()
//                .title(title)
//                .content(content)
//                .type(type)
//                .build();
//
//    }


}
