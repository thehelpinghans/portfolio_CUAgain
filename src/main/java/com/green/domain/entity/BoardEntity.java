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
@Table(name = "board")
@Entity
public class BoardEntity extends  BaseDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //게시글 번호

    @Column(nullable = false)
    private String title; //제목

    @Lob
    @Column(nullable = false)
    private String content; //내용

    @Column(nullable = false)
    private long readCount; //조회수

    @Enumerated(EnumType.STRING)
    private BoardStatus status; //상태  0,1,2 정상/삭제/비활성화

    @Column(nullable = false)
    private boolean pin; //상단고정 여부 true,false 고정/비고정

    @Enumerated(EnumType.STRING)
    private Type type; //게시판 종류  공지사항/자유게시판

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeesEntity employees;




}
