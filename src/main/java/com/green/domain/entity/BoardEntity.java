package com.green.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
@Entity
public class BoardEntity extends  BaseDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //게시글 번호

    @Column(nullable = false)
    private String title; //제목

    @Lob
    @Column(nullable = false)
    private String content; //내용

    @Column(nullable = false)
    private long readCount; //조회수

   //@Enumerated(EnumType.STRING)
   //private BoardStatus status; //상태  0,1,2 정상/삭제/비활성화

//
    private boolean pin;   //true or false


    @Enumerated(EnumType.STRING)
    private BoardType type; //게시판 종류  공지사항/자유게시판

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private EmployeesEntity writer;

    @OneToMany(mappedBy = "board" , fetch = FetchType.EAGER)
    @OrderBy("id asc")
    @Builder.Default
    private List<ReplyEntity> reply= new ArrayList<>();



    public BoardEntity setTitle(String title){
        this.title=title;
        return this;
    }
    public BoardEntity setContent(String content){
        this.content=content;
        return this;
    }


    public BoardEntity setReadCount(long resultCount) {
        this.readCount = resultCount;
        return this;
    }
}
