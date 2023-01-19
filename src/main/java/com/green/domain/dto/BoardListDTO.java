package com.green.domain.dto;

import com.green.domain.entity.BoardEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListDTO {

    private long id;
    private String name;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private long readCount;
    private String type;

    public BoardListDTO() {
    }

    public BoardListDTO(BoardEntity e){
        this.id=e.getId();
        this.title = e.getTitle();
        this.name=e.getWriter().getName();  //조인컬럼이 있어서 가능!
        this.content = e.getContent();
        this.createdDate=e.getCreatedDate();
        this.readCount=e.getReadCount();
        this.type=e.getType().name();

    }

}
