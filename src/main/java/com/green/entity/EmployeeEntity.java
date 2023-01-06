package com.green.entity;



import java.util.Set;


import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "CA_employee")
@Entity
public class EmployeeEntity { //사원
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private long phone;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String pass;
	
	
	@JoinColumn//fk
	@ManyToOne //가져올때 사용?
	private DepartmentEntity department;
	
	//@CollectionTable(name= "CA_role")
	//@Builder.Default
	//@Enumerated(EnumType.STRING) //설정하지않으면 숫자로 나옴
	//@ElementCollection(fetch = FetchType.EAGER)//1:N관계
//	Set<CARole> roles= new HashSet<>();
//	
//	public EmployeeEntity addRole(CARole role) {
//		roles.add(role);   //권한
//		return this;
//	}
	
	
 
	
	
}
