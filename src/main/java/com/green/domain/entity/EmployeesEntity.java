package com.green.domain.entity;


import com.green.security.MyRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "employees")
@Entity
public class EmployeesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;   //사원번호

    @Column(unique = true, nullable = false)   //unique not null
    private String email;   //username
    @Column(nullable = false)   //not null
    private String pass;    //password
    @Column(nullable = false)   //not null
    private String name;
    private LocalDate hireDate;  //입사일
    private boolean end ;    // 퇴사여부
    private LocalDate endDate;  //퇴사일
    private String phone;    // 연락처

    @JoinColumn //image_id
    @OneToOne
    private ImagesEntity image;

    @Enumerated(EnumType.STRING)    //직책
    @ManyToOne//속한 팀
	@JoinColumn(name ="team_id")
	private TeamEntity team;

    @ManyToOne//속한 부서
	@JoinColumn(name ="dep_id")
	private DepartmentEntity department;


    @Enumerated(EnumType.STRING)
    private Position position;
    //ROLE정보 --enum 사용

    @Builder.Default
    @CollectionTable(name = "role")
    @Enumerated(EnumType.STRING)    //저장유형 문자열로(롤 확장시 유리) 기본 ordinal(숫자)
    @ElementCollection(fetch = FetchType.EAGER) //1:N member테이블에서만 접근가능한 내장테이블?로 만들어줌
    private Set<MyRole> roles = new HashSet<>();

    //role적용을 위한 편의메서드
    public EmployeesEntity addRole(MyRole role){
        this.roles.add(role);
        return this;
    }


    @JoinColumn //address_id
    @OneToOne
    private AddressEntity address;  //주소

    /*
    //배송지정보
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<DeliveryEntity> delivery = new ArrayList<>();

    public MemberEntity addAddress(DeliveryEntity dest){
        this.delivery.add(dest);
        return this;
    }
*/

}
