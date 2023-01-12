package com.green.service;

import com.green.domain.dto.BoardInsertDTO;
import com.green.domain.dto.BoardListDTO;
import org.springframework.ui.Model;

public interface BoardService {

    

    void boardList(String type, Model model);

    void boardListDetail(Long boardId, Model model);

    void getBoardDetail(long boardId, Model model);

    void boardUpdate(long boardId, BoardListDTO dto);

    void save(BoardInsertDTO dto, long id);


    void freeList(Model model);


}
