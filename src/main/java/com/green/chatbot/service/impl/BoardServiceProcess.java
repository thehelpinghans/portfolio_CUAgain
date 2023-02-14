package com.green.chatbot.service.impl;

import com.green.chatbot.service.BoardService;
import com.green.domain.dto.*;
import com.green.domain.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceProcess implements BoardService {

    @Autowired
    private BoardEntityRepository boardRepo;

    @Autowired
    private EmployeesEntityRepository empRepo;

    @Autowired
    private ReplyRepository repRepo;

    @Override
    @Transactional(readOnly = true)
    public Page<BoardEntity> pageList(BoardType type, Pageable pageable){
        return boardRepo.findByType(type, pageable);
    }


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

        BoardEntity entity=boardRepo.findById(boardId).orElseThrow();
        boardRepo.save(entity.setReadCount(entity.getReadCount()+1));
       BoardDetailDTO dto=new BoardDetailDTO(entity);
        model.addAttribute("detail", dto);

        model.addAttribute("list", repRepo.findByBoardId(boardId).stream().map(ReplyListDTO::new)
                .collect(Collectors.toList()));
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
                        .writer(emp)
                .build());
    }
    @Transactional
	@Override
	public void getBoardListBySearch(String type, String data,long boardType, Model model) {
        BoardType boardTypeEnum;
        if(boardType==0)
            boardTypeEnum=BoardType.공지사항;
        else
            boardTypeEnum=BoardType.자유게시판;

		if(type.equals("title")) {
			List<BoardListDTO> list= boardRepo.findByTitleContainingAndType(data,boardTypeEnum).stream()
					.map(e-> new BoardListDTO(e)).collect(Collectors.toList());
			model.addAttribute("list", list);
		} else if(type.equals("writer")) {
			List<BoardListDTO> list= boardRepo.findByWriterNameContainingAndType(data,boardTypeEnum)
                    .stream()
					.map(e-> new BoardListDTO(e))
                    .collect(Collectors.toList());
			model.addAttribute("list", list);
		}else {
            BoardListDTO dto=boardRepo.findByIdAndType(Long.valueOf(data),boardTypeEnum)
                    .map(BoardListDTO::new)
                    .orElseThrow();
            model.addAttribute("list", dto);

        }

	}

    @Override
    public void replyReg(ReplyInsertDTO dto, Model model) {

        //댓글 등록 완료
        repRepo.save(ReplyEntity.builder()
                        .board(boardRepo.findById(dto.getBoardId()).get())
                        .content(dto.getComment())
                        .writer(empRepo.findById(dto.getWriteId()).get())
                .build());

        model.addAttribute("list", repRepo.findByBoardId(dto.getBoardId()).stream()
                .map(ReplyListDTO::new).collect(Collectors.toList()));

    }

//    @Override
//    @Transactional(readOnly = true)
//    public Page<BoardEntity> pageList(Pageable pageable) {
//        return boardRepo.findAll(pageable);
//    }
   
    
//    //페이징처리
//	@Override
//	public Page<BoardListDTO> paging(Pageable pageable) {
//		int page= pageable.getPageNumber()-1;
//		int pageLimit= 3;
//		
//		Page<BoardEntity> boardEntity= 
//				boardRepo.findAll(PageRequest.of(page, pageLimit, Sort.by(org.springframework.data.domain.Sort.Direction.DESC, "id")));
		
//	}
    
    




}
