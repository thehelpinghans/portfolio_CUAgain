package com.green.domain.entity;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.green.domain.dto.AdminAttendanceListDTO;
import com.green.domain.dto.AttendanceListDTO;
import com.green.domain.dto.AttendanceListRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "attendance")
@Entity
public class AttendanceEntity{
	//근태pk, 사원번호, 부서, 팀, 직책, 사원명, 날짜, 출근시간, 퇴근시간, 상태 
	
	//근태pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	//사원번호
	@JoinColumn
	@ManyToOne(cascade = CascadeType.DETACH)
	private EmployeesEntity employee;

	//날짜
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDate date;
	
	//출근시간
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date inTime;
	
	//퇴근시간
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date outTime; 
	
	//상태
	@Column(columnDefinition = "VARCHAR(255) default '출근'")
	private String attendStatus;
	
	
	@PrePersist
	public void attendStatus() {
		this.attendStatus = this.attendStatus == null ? "출근" : this.attendStatus;
	}
	

	public AttendanceEntity employeeId(EmployeesEntity employee) {
		this.employee = employee;
		return this;
	}


	public void checkStatus() {
		//출근+9시간 check
		Date check=Date.from(this.inTime.toInstant().plus(Duration.ofHours(9)));
		//출근+9시간인걸 퇴근시간 이전인지 비교
		System.out.println(inTime);
		System.out.println(check.before(this.outTime));
		//퇴근시간이 check시간보다 이전이면 조퇴
		if(this.outTime.before(check)) {
			this.attendStatus = "조퇴";
			return;
		}
		this.attendStatus = "퇴근";
	}
	
	public AttendanceListDTO toListDTO() {
		return AttendanceListDTO.builder()
		.date(date)
		.inTime(inTime)
		.outTime(outTime)
		.attendStatus(attendStatus)
		.build();
	}
	
//	public AttendanceEntity toListDTO2(AdminAttendanceListDTO dto) {
//		 this.employee.setId(dto.getEmployeeId());
//		 this.employee.setName(dto.getName());
//		 this.employee.getDep().setName(dto.getDepartment());
//		 //this.employee.getPosition().getPosition();
//		 this.inTime=dto.getInTime();
//		 this.outTime=dto.getOutTime();
//		 this.attendStatus=dto.getAttendStatus();
//		 return this;
//	}
	
}
