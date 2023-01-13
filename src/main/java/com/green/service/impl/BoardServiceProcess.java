package com.green.service.impl;

import com.green.domain.dto.BoardDetailDTO;
import com.green.domain.dto.BoardInsertDTO;
import com.green.domain.dto.BoardListDTO;
import com.green.domain.entity.*;
import com.green.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.stream.Collectors;

@Service
public class BoardServiceProcess implements BoardService {

    @Autowired
    private BoardEntityRepository boardRepo;

    @Autowired
    private EmployeesEntityRepository empRepo;

//    @Override
//    public void save(BoardInsertDTO dto, long eid) {
//        boardRepo.save(BoardEntity.builder()
//                        .title(dto.getTitle())
//                        .content(dto.getContent())
//                        .type(BoardType.공지사항)
//                        .employees(empRepo.findById(eid).get())
//
//                .build());
//    }

    //등록한 값이 공지사항 리스트에 전달해주는 기능
    @Override
    public void boardList(String type, Model model) {

        if(type.equals("공지사항")) {
            model.addAttribute("boards", boardRepo.findByType(BoardType.공지사항).stream().map(BoardListDTO::new)
                    .collect(Collectors.toList()));
        }
        else{
            model.addAttribute("boards", boardRepo.findByType(BoardType.자유게시판).stream().map(BoardListDTO::new)
                    .collect(Collectors.toList()));
        }

    }

//    //등록한 값이 자유게시판 리스트에 전달해주는 기능
//    @Override
//    public void freeList(Model model) {
//        model.addAttribute("frees", boardRepo.findByType(BoardType.자유게시판).stream().map(BoardListDTO::new)
//                .collect(Collectors.toList()));
//
//    }

    //공지사항에 등록한 값을 detail에 전달하는 기능?
    @Override
    public void boardListDetail(Long boardId, Model model) {
        model.addAttribute("detail", boardRepo.findById(boardId)
                .map(BoardDetailDTO::new).orElseThrow());
    }

    //공지사항 값을  수정하기위해서 수정버튼 눌렀을때 값 그대로 가져오기위한 기능
    @Override
    public void getBoardDetail(long boardId, Model model) {

        BoardDetailDTO dto=boardRepo.findById(boardId).map(BoardDetailDTO::new).get();
        model.addAttribute("dto", dto);
    }

    //공지사항 수정하고 나서 저장해주는 기능
    @Override
    public void boardUpdate(long boardId, BoardListDTO dto) {
            boardRepo.save(boardRepo.findById(boardId).orElseThrow()
                    .setTitle(dto.getTitle()).setContent(dto.getContent()));

    }
    //공지사항 등록 정보 저장
    @Override
    public void save(BoardInsertDTO dto, long id) {
        EmployeesEntity emp = empRepo.findById(id).orElseThrow();

        boardRepo.save(BoardEntity.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .type(BoardType.valueOf(dto.getType().toString()))
                        .employees(emp)
                .build());
    }




}
