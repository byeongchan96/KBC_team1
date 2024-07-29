package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardCommentEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BoardService {


    List<BoardEntity> selectBoardList() throws Exception;

    Page<BoardEntity> selectBoardList(Pageable pageable) throws Exception;

    BoardEntity selectBoardDetail(Long boardId) throws Exception;

    void deleteBoardById(long boardId) throws Exception;

    List<BoardEntity> selectBoardListByCate(String cate) throws Exception;

    void insertBoard(BoardEntity board) throws Exception;

    void updateBoard(BoardEntity board) throws Exception;

    Optional<BoardEntity> findAllByTitle(String searchString) throws Exception;

    List<BoardEntity> searchCateListBoard(Long boardId, String cate)throws Exception;

    void boardCommentWrite(BoardCommentEntity board) throws Exception;

    void boardCommentUpdate(BoardCommentEntity board) throws Exception;

    void boardCommentDelete(Long boardId) throws Exception;

    List<BoardEntity> userBoardList(Long userId) throws Exception;

    List<BoardEntity> userLikeBoardList(Long userId) throws Exception;

    List<BoardEntity> movieBookmarkList(Long userId) throws Exception;
}
