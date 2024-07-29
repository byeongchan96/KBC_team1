package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardCommentEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import bitc.fullstack405.bitcteam3prj.service.BoardService;
import bitc.fullstack405.bitcteam3prj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    //     게시판 댓글 작성
    @PostMapping("/write")
    public String boardCommentWrite(Long boardId, String userId, String contents) throws Exception {
        BoardCommentEntity comment = new BoardCommentEntity();



        UserEntity user = userService.findByUserId(userId);
        BoardEntity board = boardService.selectBoardDetail(boardId);

        comment.setUser(user);
        comment.setContents(contents);
        comment.setBoard(board);

        boardService.boardCommentWrite(comment);

        return "redirect:/board/" + boardId;
    }

    //    게시판 댓글 수정
    @PutMapping("/{commentId}")
    public String boardCommentUpdate(@PathVariable("commentId") int boardId, @PathVariable("commentId") String contents, BoardCommentEntity board) throws Exception {
        board.setId(boardId);
        board.setContents(contents);
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
