package com.green.controller;

import com.green.domain.dto.BoardInsertDTO;
import com.green.domain.dto.BoardListDTO;
import com.green.security.MyUserDetails;
import com.green.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService service;



    //공지사항 등록     후 다시 리스트페이지로 이동
    @PostMapping("/admin/board/reg")
    public String board(BoardInsertDTO dto, @AuthenticationPrincipal MyUserDetails userDetails){
        System.out.println(">>>등록>>"+dto.getType());
        service.save(dto, userDetails.getId());
        return "redirect:/admin/board/boardList/"+dto.getType();
    }

    //공지사항 등록페이지이동
    @GetMapping("/admin/board/write/{type}")
    public String write(@PathVariable String type, Model model) {
        model.addAttribute("type",type);
        return "admin/board/write";
    }

    //공지사항 리스트에서 제목 눌렀을때 상세페이지
    @GetMapping("/admin/board/view/{boardId}")
    public String boardListDetail(@PathVariable long boardId, Model model){
        service.boardListDetail(boardId, model);

        return "admin/board/view";
    }

    //공지사항 상세페이지에서 수정버튼눌렀을때의 페이지
    @GetMapping("/admin/board/{boardId}")
    public String boardmodify(@PathVariable("boardId") long boardId, Model model){
        service.getBoardDetail(boardId, model);

        return "admin/board/edit";
    }
    //리스트페이지
    @GetMapping("/admin/board/boardList/{type}")
    public String boardList(@PathVariable String type, Model model) {
        service.boardList(type, model);

        System.out.println(">>리스트>" + type);
        model.addAttribute("type",type);
        return "admin/board/notice";

    }
    //공지사항 수정완료눌렀을때의 페이지?
    @PostMapping("/admin/board/update/{boardId}")
    public String update(@PathVariable("boardId") long boardId, BoardListDTO dto){
        System.out.println(">>>>>>>>>>>>>"+boardId);
        service.boardUpdate(boardId, dto);
        return "redirect:/admin/board/boardList/";
    }


}
