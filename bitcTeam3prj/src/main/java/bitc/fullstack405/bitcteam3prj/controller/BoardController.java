package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.BoardLikeEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import bitc.fullstack405.bitcteam3prj.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private UserService userService;
    @Autowired
    private PaginationService paginationService;

    @Autowired
    private BoardLikeService boardLikeService;

    //    게시글 전체 목록
    @GetMapping({"/", ""})
    public ModelAndView selectBoardList(
            @PageableDefault(size=10, sort="createdAt") Pageable pageable,
            @RequestParam(required = false) String searchValue,
            @RequestParam(required = false) String searchCate
    ) throws Exception {
        ModelAndView mv = new ModelAndView("/board/boardList");

        Page<BoardEntity> boardList = null;

        if(searchValue == null || searchValue.isEmpty()) {
            boardList = boardService.selectBoardList(pageable);
        }
        else{
            boardList = boardService.selectBoardListBySearchValue(pageable, searchValue);
        }

        mv.addObject("boardList", boardList);
        mv.addObject("barList",
                paginationService.getPaginationBarNumbers(
                pageable.getPageNumber(),
                boardList.getTotalPages()
        ));

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
    public ModelAndView selectBoardDetail(
            HttpSession session,
            @PathVariable("boardId") Long boardId) throws Exception {
        ModelAndView mv = new ModelAndView("board/boardDetail");
        BoardEntity board = boardService.selectBoardDetail(boardId);
        board.setVisitCnt(board.getVisitCnt() + 1);
        boardService.updateBoard(board);

        BoardLikeEntity boardLike = null;
        String userId =(String)session.getAttribute("userId");
        if(!(userId == null  || userId.isEmpty())) {
            boardLike = boardLikeService.findByUserIdAndBoardId(
                    userService.findByUserId((String) session.getAttribute("userId")).getId(),
                    boardId
            );
        }

        String userLike = "";
        if(boardLike != null){
            userLike = boardLike.getLikeYn();
        }

        var boardLikeList = boardLikeService.findAllByBoardId(boardId);
        List<BoardLikeEntity> dislikeList =  new ArrayList<>();
        List<BoardLikeEntity> likeList = new ArrayList<>();
        if(boardLikeList != null) {
            for (var like : boardLikeList) {
                if (like.getLikeYn().equals("N")) {
                    dislikeList.add(like);
                }else{
                    likeList.add(like);
                }
            }
        }



        mv.addObject("userLike", userLike);
        mv.addObject("disLikeCnt", dislikeList.size());
        mv.addObject("likeCnt", likeList.size());
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
    public String insertBoard(String userId, String title, String category, String content) throws Exception {
        BoardEntity board = new BoardEntity();

        UserEntity user = userService.findByUserId(userId);
        board.setTitle(title);
        board.setCategory(category);
        board.setContent(content);
        board.setWarning("warning");
        board.setUser(user);

        boardService.insertBoard(board);

        return "redirect:/board/";
    }

//    게시물 삭제
    @DeleteMapping("/{boardId}")
    public String deleteBoard(@PathVariable("boardId") long boardId) throws Exception {
        boardService.deleteBoardById(boardId);

        return "redirect:/board/";
    }

//    게시글 수정
    @PutMapping("/{boardId}")
    public String updateBoard(long boardId, String title, String content) throws Exception {
        BoardEntity board = boardService.selectBoardDetail(boardId);
        board.setTitle(title);
        board.setContent(content);
        board.setId(boardId);

        boardService.updateBoard(board);

        return "redirect:/board/" + boardId;
    }

//    게시판 검색
    @GetMapping("/search")
    public ModelAndView findAllByTitle(String searchString) throws Exception {
        ModelAndView mv = new ModelAndView("/board/boardList");
        Optional<BoardEntity> board =  boardService.findAllByTitle(searchString);
        mv.addObject("board" , board);

        return mv;
    }

////    게시판 카테고리 검색
//    @GetMapping("/search/{movieCate}")
//    public ModelAndView searchCateListBoard(@PathVariable("movieCate") Long boardId, @PathVariable("movieCate") String cate) throws Exception {
//        ModelAndView mv = new ModelAndView("/board/boardList");
//        List<BoardEntity> board = boardService.searchCateListBoard(boardId, cate);
//        mv.addObject("board" , board);
//
//        return mv;
//    }
}
