package com.green.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
@Entity
public class StoreEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Lob
	@Column(nullable = true)
	private String content;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private EmployeesEntity manager;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn
	private AddressEntity address;
	
	public StoreEntity setName(String name) {
		this.name=name;
		return this;
	}
	
	public StoreEntity setContent(String content) {
		this.content=content;
		return this;
	}
	
	public StoreEntity employeeId(EmployeesEntity manager) {
		this.manager = manager;
		return this;
	}
}
