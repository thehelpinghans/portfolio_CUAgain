package com.green.domain.dto;

import com.green.domain.entity.DocumentEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentListDTO {

    private long id;
    private String title;
    private String content;
    private String depName;
    private long writerId;

    private String writerName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private long acceptorId;
    private String acceptorName;
    private String docStatus;
    private String checkStatus;
    private LocalDateTime approvalDate;

    public DocumentListDTO(){

    }
    public DocumentListDTO(DocumentEntity e) {
        this.id = e.getId();
        this.title = e.getTitle();
        this.content = e.getContent();
        this.depName = e.getWriter().getDep().getName();
        this.writerId=e.getWriter().getId();
        this.writerName = e.getWriter().getName();
        this.createdDate = e.getCreatedDate();
        this.updatedDate=e.getUpdatedDate();
        this.acceptorId=e.getAcceptor().getId();
        this.acceptorName = e.getAcceptor().getName();
        switch ((int) e.getDocStatus()){
            case 0:
                this.docStatus="대기";
                break;
            case 1:
                this.docStatus="승인";
                break;
            case 2:
                this.docStatus="반려";
                break;
        }

        if (e.isCheckStatus()) {
            this.checkStatus = "확인";
        } else{
            this.checkStatus = "미확인";
        }
        this.approvalDate = e.getApprovalDate();
    }
}