package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardCommentEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import bitc.fullstack405.bitcteam3prj.database.repository.BoardCommentRepository;
import bitc.fullstack405.bitcteam3prj.database.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardCommentRepository boardCommentRepository;



    @Override
    public List<BoardEntity> selectBoardList() throws Exception {
        List<BoardEntity> boardList =  boardRepository.findAll();

        return boardList;
    }

    @Override
    public Page<BoardEntity> selectBoardList(Pageable pageable) throws Exception{
        return boardRepository.findAll(pageable);
    }

    @Override
    public BoardEntity selectBoardDetail(Long boardId) throws Exception {
//        boardService.updateHitCount(BoardId)

        Optional<BoardEntity> opt = boardRepository.findById(boardId);

        BoardEntity board = null;
        if(opt.isPresent()){
            board = opt.get();
        }
        return board;
    }

    @Override
    public void deleteBoardById(long boardId) throws Exception {
        boardRepository.deleteById(boardId);
    }

    @Override
    public List<BoardEntity> selectBoardListByCate(String cate) throws Exception {
        List<BoardEntity> boardList = boardRepository.findAllByCategory(cate);
        return boardList;
    }

    @Override
    public void insertBoard(BoardEntity board) throws Exception {
        boardRepository.save(board);
    }

    @Override
    public void updateBoard(BoardEntity board) throws Exception {
        boardRepository.save(board);
    }

    @Override
    public Optional<BoardEntity> findAllByTitle(String searchString) throws Exception {
        Optional<BoardEntity> boardList = boardRepository.findAllByTitle(searchString);
        return boardList;
    }

    @Override
    public List<BoardEntity> searchCateListBoard(Long boardId, String cate) throws Exception {
        List<BoardEntity> boardList = boardRepository.findAllByCategory(cate);
        return boardList;
    }

//    코멘트 작성 , 수정, 삭제
    @Override
    public void boardCommentWrite(BoardCommentEntity board) throws Exception {
        boardCommentRepository.save(board);
    }

    @Override
    public void boardCommentUpdate(BoardCommentEntity board) throws Exception {
        boardCommentRepository.save(board);
    }

    @Override
    public void boardCommentDelete(Long boardId) throws Exception {
        boardCommentRepository.deleteById(boardId);
    }

//    유저 게시글 칸
    @Override
    public List<BoardEntity> userBoardList(Long userId) throws Exception {
//        List<BoardEntity> boardList =
        return List.of();
    }

    @Override
    public List<BoardEntity> userLikeBoardList(Long userId) throws Exception {
        return List.of();
    }

    @Override
    public List<BoardEntity> movieBookmarkList(Long userId) throws Exception {
        return List.of();
    }
}
