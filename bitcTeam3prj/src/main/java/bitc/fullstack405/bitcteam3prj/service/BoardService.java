package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {


    List<BoardEntity> selectBoardList() throws Exception;

    BoardEntity selectBoardDetail(Long boardId) throws Exception;

    void deleteBoardById(long boardId) throws Exception;

    List<BoardEntity> selectBoardListByCate(String cate) throws Exception;

    void insertBoard(BoardEntity board) throws Exception;

    void updateBoard(BoardEntity board) throws Exception;

//    List<BoardEntity> selectBoardListById() throws Exception;

}
