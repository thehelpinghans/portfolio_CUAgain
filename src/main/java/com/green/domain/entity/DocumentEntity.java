package com.green.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "document")
@Entity
public class DocumentEntity{
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	@Column(nullable = false)
	private String title; //문서이름
	@Lob
	@Column(nullable = false)
	private String content; //문서내용
	@Column(nullable = false)
	private long docStatus; //문서상태 0:대기,1:승인,2:반려
	private boolean checkStatus; //결재자 확인상태 0:미확인, 1:확인
	private LocalDateTime approvalDate;
	@CreationTimestamp
	private LocalDateTime createdDate;  //최초작성일
	@CreationTimestamp
	private LocalDateTime updatedDate;  //최종수정일
	@JoinColumn //employees_id
	@ManyToOne
	private EmployeesEntity writer; //사원번호(작성자)
	@JoinColumn	//acceptor_id
	@ManyToOne
	private EmployeesEntity acceptor; //결재자

	public DocumentEntity setCheckStatus(boolean b) {
		this.checkStatus=b;
		return this;
	}

	public DocumentEntity setDocStatus(long docStatus) {
		this.docStatus=docStatus;
		return this;
	}

	public DocumentEntity setApprovalDate() {
		this.approvalDate=LocalDateTime.now();
		return this;
	}

	public DocumentEntity update(String title, String content) {
		this.title=title;
		this.content=content;
		this.updatedDate=LocalDateTime.now();
		return this;
	}
}
