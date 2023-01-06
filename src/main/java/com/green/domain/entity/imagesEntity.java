package com.green.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class imagesEntity  extends BaseDateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orgName;
    private String newName;
    private String url;

    public imagesEntity update(String newName, String orgName) {
        this.newName=newName;
        this.orgName=orgName;
        return this;
    }
}

