package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import bitc.fullstack405.bitcteam3prj.database.entity.BoardLikeEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
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

    List<BoardEntity> findAllByUserId(Long userId) throws Exception;

    List<UserEntity> findAllByUserBoardLikeList(Long userId) throws Exception;

    List<BoardLikeEntity> findAllByUserBoardLikeList(List<BoardLikeEntity> boardLikeList);

    Page<BoardEntity> selectBoardListBySearchValue(Pageable pageable, String searchValue) throws Exception;
}
