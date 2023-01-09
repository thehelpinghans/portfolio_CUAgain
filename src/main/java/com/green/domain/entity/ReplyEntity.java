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

    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private BoardEntity board;

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeesEntity employees;




}
