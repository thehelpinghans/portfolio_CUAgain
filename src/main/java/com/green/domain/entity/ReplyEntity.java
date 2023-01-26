package com.green.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reply")
@Entity
public class ReplyEntity extends BaseDateEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String content;


    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private BoardEntity board; // 게시글번호

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeesEntity writer; //작성자




}
