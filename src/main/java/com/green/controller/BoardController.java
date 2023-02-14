package com.green.controller;

import com.green.domain.dto.BoardInsertDTO;
import com.green.domain.dto.BoardListDTO;
import com.green.domain.dto.ReplyInsertDTO;
import com.green.domain.entity.BoardEntity;
import com.green.domain.entity.BoardEntityRepository;
import com.green.domain.entity.BoardType;
import com.green.security.MyUserDetails;
import com.green.chatbot.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    @Autowired
    private BoardEntityRepository boardRepo;

//    @GetMapping("/")
//    public String paging(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC)
//                         Pageable pageable){
//        model.addAttribute("pages", service.pageList(pageable));
//
//        return "admin/board/notice";
//    }



    // 등록페이지이동
    @GetMapping("/member/board/write/{type}")
    public String write(@PathVariable String type, Model model) {
        model.addAttribute("type",type);
        return "admin/board/write";
    }

    //공지사항 리스트에서 제목 눌렀을때 상세페이지
    @GetMapping("/member/board/view/{boardId}")
    public String boardListDetail(@PathVariable long boardId, Model model){
        service.boardListDetail(boardId, model);

        return "admin/board/view";
    }

    //공지사항 상세페이지에서 수정버튼눌렀을때의 페이지
    @GetMapping("/member/board/{boardId}")
    public String boardmodify(@PathVariable("boardId") long boardId, Model model){
        service.getBoardDetail(boardId, model);

        return "admin/board/edit";
    }
    //리스트페이지
    @GetMapping("/member/board/boardList/{lType}")
    public String boardList(@PathVariable long lType, Model model, @PageableDefault(sort = "id", size = 4, direction = Sort.Direction.DESC)
    Pageable pageable) {

        BoardType type;
        if(lType==0){
            type=BoardType.공지사항;
        }else{
            type =BoardType.자유게시판;
        }
        //service.boardList(String.valueOf(type), model);
        System.out.println(">>리스트>" + type);
        //type.ordinal();
        model.addAttribute("type",type.name());

        Page<BoardEntity> list2= service.pageList(type, pageable);
        Page<BoardListDTO> list=list2.map(e->new BoardListDTO(e));
        model.addAttribute("pages", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        System.out.println(">hasnext>>"+list.hasNext());
        System.out.println(">hasprevious>>"+list.hasPrevious());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());

        return "admin/board/notice";

    }
    //공지사항 수정완료눌렀을때의 페이지?
    @PostMapping("/member/board/update/{boardId}")
    public String update(@PathVariable("boardId") long boardId, BoardListDTO dto){
        System.out.println(">>>>>>>>>>>>>"+boardId);
        service.boardUpdate(boardId, dto);
        long lType;
        if(dto.getType().equals("공지사항")){
            lType=0;
        }else{
            lType=1;
        }
        return "redirect:/member/board/boardList/"+lType;
    }
    //공지사항 등록     후 다시 리스트페이지로 이동
    @PostMapping("/member/board/reg")
    public String board(BoardInsertDTO dto, @AuthenticationPrincipal MyUserDetails userDetails){
        System.out.println(">>>등록>>"+dto.getType());
        service.save(dto, userDetails.getId());

        long lType;
        if(dto.getType().equals("공지사항")){
            lType=0;
        }else{
            lType=1;
        }
        return "redirect:/member/board/boardList/"+lType;
    }
    //목록에서 제목,번호,작성자로 검색하여 찾는 기능구현
    @GetMapping("/member/board/searchList/{type}/{data}/{boardType}")
    public String boardListBySearch(@PathVariable String type, @PathVariable String data, @PathVariable long boardType, Model model) {
    	service.getBoardListBySearch(type,data,boardType,model);
    	return "admin/board/searchResult";
    }
    //댓글 등록 기능구현
    @PostMapping("/member/comment/reg")
    public String replyReg(/*@RequestBody*/ ReplyInsertDTO dto, @AuthenticationPrincipal MyUserDetails myUserDetails, Model model){
        System.out.println(">>>>boardId" + dto.getBoardId());
        System.out.println(">>>>comment" + dto.getComment());
        dto.setWriteId(myUserDetails.getId());

        service.replyReg(dto, model);
        return "admin/reply/replyList";
    }


}
