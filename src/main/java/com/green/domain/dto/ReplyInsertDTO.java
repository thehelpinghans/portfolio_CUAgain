package com.green.domain.dto;

import lombok.Data;

@Data
public class ReplyInsertDTO {
    private long boardId;  //게시판 번호
    private String comment; //댓글
    private long writeId;  //작성자 번호

}

