package com.green.domain.dto;

import com.green.domain.entity.BoardEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDetailDTO {

    private long id;
    private String title;
    private String name;
    private String content;
    private LocalDateTime createdDate;
    private long readCount;

    public BoardDetailDTO(BoardEntity e){
        this.id=e.getId();
        this.title = e.getTitle();
        this.name=e.getEmployees().getName();
        this.content = e.getContent();
        this.createdDate=e.getCreatedDate();
        this.readCount=e.getReadCount();
    }
}
