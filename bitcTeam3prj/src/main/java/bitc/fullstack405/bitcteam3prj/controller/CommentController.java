package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardCommentEntity;
import bitc.fullstack405.bitcteam3prj.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private BoardService boardService;

    //     게시판 댓글 작성
    @PostMapping("/{commentId}")
    public String boardCommentWrite(@PathVariable("commentId") int boardId, @PathVariable("commentId") String content, BoardCommentEntity board) throws Exception {
        board.setId(boardId);
        board.setContents(content);
        boardService.boardCommentWrite(board);

        return "redirect:/board/boardDetail";
    }

    //    게시판 댓글 수정
    @PutMapping("/{commentId}")
    public String boardCommentUpdate(@PathVariable("commentId") int boardId, @PathVariable("commentId") String content, BoardCommentEntity board) throws Exception {
        board.setId(boardId);
        board.setContents(content);
        boardService.boardCommentUpdate(board);

        return "redirect:/board/boardDetail";
    }

    //    게시판 댓글 삭제
    @DeleteMapping("/{commentId}")
    public String boardCommentDelete(@PathVariable("commentId") Long boardId) throws Exception {
        boardService.boardCommentDelete(boardId);

        return "redirect:/board/boardList";
    }
}
