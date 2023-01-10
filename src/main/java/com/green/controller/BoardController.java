package com.green.controller;

import com.green.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BoardController {

    @Autowired
    private BoardService service;

//    @PostMapping("")
//    public String board(BoardInsertDTO dto){
//        return "";
//    }

}
