package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import bitc.fullstack405.bitcteam3prj.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

//    게시글 전체 목록
    @GetMapping({"/", ""})
    public ModelAndView selectBoardList(
            @PageableDefault(size=10, sort="createdAt") Pageable pageable
    ) throws Exception {
        ModelAndView mv = new ModelAndView("/board/boardList");

//        List<BoardEntity> boardList = boardService.selectBoardList();

        Page<BoardEntity> boardList = boardService.selectBoardList(pageable);

        mv.addObject("boardList", boardList);

        return mv;
    }

////    게시글 목록(카테고리)
//    @GetMapping("/{boardCate}")
//    public ModelAndView selectBoardList(@PathVariable("boardCate") String boardCate) throws Exception {
//        ModelAndView mv = new ModelAndView("/board/boardList");
//        List<BoardEntity> boardList = boardService.selectBoardListByCate(boardCate);
//        mv.addObject("boardList" , boardList);
//
//        return mv;
//    }

//    게시글 상세보기
    @GetMapping("/{boardId}")
    public ModelAndView selectBoardDetail(@PathVariable("boardId") Long boardId) throws Exception {
        ModelAndView mv = new ModelAndView("board/boardDetail");
        BoardEntity board = boardService.selectBoardDetail(boardId);
        mv.addObject("board" , board);

        return mv;
    }

//    게시글 등록(view)
    @GetMapping("/write")
    public String insertBoard() throws Exception {
        return "board/boardWrite";
    }

//    게시글 등록 처리
    @PostMapping("/write")
    public String insertBoard(BoardEntity board) throws Exception {
        boardService.insertBoard(board);

        return "redirect:/board/boardList";
    }

//    게시물 삭제
    @DeleteMapping("/{boardId}")
    public String deleteBoard(@PathVariable("boardId") long boardId) throws Exception {
        boardService.deleteBoardById(boardId);

        return "redirect:/board/boardList";
    }

//    게시글 수정
    @PutMapping("/{boardId}")
    public String updateBoard(@PathVariable("boardId") long boardId, BoardEntity board) throws Exception {
        board.setId(boardId);
        boardService.updateBoard(board);

        return "redirect:/board/boardList";
    }

//    게시판 검색
    @GetMapping("/search")
    public ModelAndView findAllByTitle(String searchString) throws Exception {
        ModelAndView mv = new ModelAndView("/board/boardList");
        Optional<BoardEntity> board =  boardService.findAllByTitle(searchString);
        mv.addObject("board" , board);

        return mv;
    }

//    게시판 카테고리 검색
//    @GetMapping("/{movieCate}")
//    public ModelAndView searchCateListBoard(@PathVariable("movieCate") Long boardId, @PathVariable("movieCate") String cate) throws Exception {
//        ModelAndView mv = new ModelAndView("/board/boardList");
//        List<BoardEntity> board = boardService.searchCateListBoard(boardId, cate);
//        mv.addObject("board" , board);
//
//        return mv;
//    }
}
