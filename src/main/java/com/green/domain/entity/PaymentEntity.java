package com.green.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DOC_agreement")
@Entity
public class PaymentEntity extends BaseDateEntity{
	
	@Id
	long id;
	
	@Column(nullable = false)
	String title;
	
	@Column(nullable = false)
	String content;
	
	@Column(nullable = false)
	long status;
	
}
