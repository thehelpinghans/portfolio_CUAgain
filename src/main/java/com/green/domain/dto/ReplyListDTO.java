package com.green.domain.dto;

import com.green.domain.entity.ReplyEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyListDTO {
    private long id;
    private String content;
    private LocalDateTime createdDate;
    private String writer;

    public ReplyListDTO(ReplyEntity e) {
        this.id = e.getId();
        this.content = e.getContent();
        this.createdDate = e.getCreatedDate();
        this.writer=e.getWriter().getName();
    }
}
