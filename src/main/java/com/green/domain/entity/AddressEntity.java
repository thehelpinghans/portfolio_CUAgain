package com.green.domain.entity;


import com.green.domain.dto.EmployeesDetailDTO;
import com.green.domain.dto.StoreSaveDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "address")
@Entity
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String postcode;
    @Column(nullable = false)
    private String roadAddress;
    private String jibunAddress;
    @Column(nullable = false)
    private String detailAddress;
    private String extraAddress;


    public AddressEntity update(EmployeesDetailDTO e){
        this.postcode=e.getPostcode();
        this.roadAddress=e.getRoadAddress();
        this.jibunAddress=e.getJibunAddress();
        this.detailAddress=e.getDetailAddress();
        this.extraAddress=e.getExtraAddress();
        return this;
    }
    
    public AddressEntity update(StoreSaveDTO e){
        this.postcode=e.getPostcode();
        this.roadAddress=e.getRoadAddress();
        this.jibunAddress=e.getJibunAddress();
        this.detailAddress=e.getDetailAddress();
        this.extraAddress=e.getExtraAddress();
        return this;
    }
}
