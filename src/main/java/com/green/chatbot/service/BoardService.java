package com.green.chatbot.service;

import com.green.domain.dto.BoardInsertDTO;
import com.green.domain.dto.BoardListDTO;

import com.green.domain.dto.ReplyInsertDTO;
import com.green.domain.entity.BoardEntity;
import com.green.domain.entity.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;

public interface BoardService {

    Page<BoardEntity> pageList(BoardType type, Pageable pageable);

    void boardList(String type, Model model);

    void boardListDetail(Long boardId, Model model);

    void getBoardDetail(long boardId, Model model);

    void boardUpdate(long boardId, BoardListDTO dto);

    void save(BoardInsertDTO dto, long id);

	void getBoardListBySearch(String type, String data,long boardType, Model model);

    //void replyReg(long boardId, String comment, long id, Model model);

    void replyReg(ReplyInsertDTO dto, Model model);

    //Object pageList(Pageable pageable);

	//Page<BoardListDTO> paging(Pageable pageable);


    //void freeList(Model model);


}
