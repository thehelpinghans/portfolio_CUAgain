package com.green.domain.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.green.domain.dto.CalendarDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Calendar")
@Entity
public class CalendarEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;//pk
	
	//사원번호, 사원이름, 부서명
	@JoinColumn
	@ManyToOne(cascade = CascadeType.DETACH)
	private EmployeesEntity employee;
	
	@Column(nullable = false)
	private String content; //내용
	
	//일정 시작시간
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	
	//일정 종료시간
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	
	public CalendarDetailDTO toDTO() {
        return CalendarDetailDTO.builder()
        .id(this.id)
        .content(content)
        .start(this.start)
        .end(this.end)
        .build();
    }
}
