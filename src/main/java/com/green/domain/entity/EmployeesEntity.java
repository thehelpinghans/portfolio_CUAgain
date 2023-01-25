                                                                                                                  package com.green.domain.entity;


import com.green.domain.dto.EmployeesDetailDTO;
import com.green.domain.dto.StoreSaveDTO;
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
	private DepartmentEntity dep;
    
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
    public EmployeesEntity removeRole(MyRole role){
        this.roles.remove(role);
        return this;
    }

    @JoinColumn //address_id
    @OneToOne
    private AddressEntity address;  //주소

    public EmployeesEntity update(EmployeesDetailDTO dto){

        this.email = dto.getEmail();
        this.name = dto.getName();
        this.hireDate = LocalDate.parse(dto.getHireDate());
        this.phone = dto.getPhone();
        this.position = Position.valueOf(dto.getPosition());

        if(dto.getPosition().equals("사원")||dto.getPosition().equals("점장")||
                dto.getPosition().equals("매니저")||dto.getPosition().equals("직원")){
            this.removeRole(MyRole.ADMIN);
        }else{
            this.addRole(MyRole.ADMIN);
        }

        this.end=dto.isEnd();
        if(dto.isEnd()){//퇴직일시  //값있을듯?
            this.endDate= LocalDate.parse(dto.getEndDate());
        }else{  //재직일시
            this.endDate=null;
        }
        return this;
    }
    public void additionalUpdate(ImagesEntity image, TeamEntity team, DepartmentEntity dep, AddressEntity address){
        this.image=image;
        this.team=team;
        this.dep=dep;
        this.address=address;
    }

    public void updatePass(String pass) {
        this.pass=pass;
    }
    
    //캘린더
    public EmployeesDetailDTO toEmployDTO() {
        return EmployeesDetailDTO.builder()
        .id(id)
        .name(name)
        .team(this.team.getName())
        .build();
    }
}
