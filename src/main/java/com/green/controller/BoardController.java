package com.green.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.green.Service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardService service;

//    @PostMapping("")
//    public String board(BoardInsertDTO dto){
//        return "";
//    }

}
