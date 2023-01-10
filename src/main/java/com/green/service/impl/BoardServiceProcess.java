package com.green.service.impl;

import com.green.domain.dto.BoardDetailDTO;
import com.green.domain.dto.BoardInsertDTO;
import com.green.domain.dto.BoardListDTO;
import com.green.domain.entity.*;
import com.green.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class BoardServiceProcess implements BoardService {

    @Autowired
    private BoardEntityRepository boardRepo;

    @Autowired
    private EmployeesEntityRepository empRepo;

    @Override
    public void save(BoardInsertDTO dto, long eid) {
        boardRepo.save(BoardEntity.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .type(BoardType.공지사항)
                        .status(BoardStatus.정상)
                        .employees(empRepo.findById(eid).get())

                .build());
    }


    @Override
    public void boardList(Model model) { //게시판에 보여지게하는 기능 구현
        model.addAttribute("boards", boardRepo.findAll().stream().map(BoardListDTO::new)
                .collect(Collectors.toList()));
    }

    @Override
    public void boardListDetail(Long id, Model model) {
        model.addAttribute("detail", boardRepo.findById(id)
                .map(BoardDetailDTO::new).orElseThrow());
    }

    @Override
    public void getBoardDetail(long boardId, Model model) {

        BoardDetailDTO dto=boardRepo.findById(boardId).map(BoardDetailDTO::new).get();
        model.addAttribute("dto", dto);
    }

    @Override
    public void boardUpdate(long boardId, BoardListDTO dto) {
            boardRepo.save(boardRepo.findById(boardId).orElseThrow()
                    .setTitle(dto.getTitle()).setContent(dto.getContent()));

    }


}
