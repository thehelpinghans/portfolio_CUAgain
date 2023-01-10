package com.green.Service;

import com.green.domain.dto.BoardDetailDTO;
import com.green.domain.dto.BoardInsertDTO;
import com.green.domain.dto.BoardListDTO;
import org.springframework.ui.Model;

public interface BoardService {

    void save(BoardInsertDTO dto, long l);

    void boardList(Model model);

    void boardListDetail(Long id, Model model);


    void getBoardDetail(long boardId, Model model);

    void boardUpdate(long boardId, BoardListDTO dto);
}
