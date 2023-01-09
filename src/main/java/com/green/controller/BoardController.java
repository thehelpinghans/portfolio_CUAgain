package com.green.controller;

import com.green.dto.BoardInsertDTO;
import com.green.service.BoardService;
import com.green.service.impl.BoardServiceProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService service;

//    @PostMapping("")
//    public String board(BoardInsertDTO dto){
//        return "";
//    }

}
