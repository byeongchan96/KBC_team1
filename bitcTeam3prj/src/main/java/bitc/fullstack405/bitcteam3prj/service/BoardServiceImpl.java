package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.BoardLikeEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import bitc.fullstack405.bitcteam3prj.database.repository.BoardLikeRepository;
import bitc.fullstack405.bitcteam3prj.database.repository.BoardRepository;
import bitc.fullstack405.bitcteam3prj.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardLikeRepository boardLikeRepository;
    @Autowired
    private UserRepository userRepository;

    //    게시판 전체 목록
    @Override
    public List<BoardEntity> selectBoardList() throws Exception {
        List<BoardEntity> boardList =  boardRepository.findAll();

        return boardList;
    }

//    게시글 상세보기
    @Override
    public BoardEntity selectBoardDetail(Long boardId) throws Exception {

        Optional<BoardEntity> opt = boardRepository.findById(boardId);

        BoardEntity board = null;
        if(opt.isPresent()){
            board = opt.get();
        }
        return board;
    }

//    게시글 삭제
    @Override
    public void deleteBoardById(long boardId) throws Exception {
        boardRepository.deleteById(boardId);
    }

//    게시글 목록(카테고리)
    @Override
    public List<BoardEntity> selectBoardListByCate(String cate) throws Exception {
        List<BoardEntity> boardList = boardRepository.findAllByCategory(cate);
        return boardList;
    }

//    게시글 등록 처리
    @Override
    public void insertBoard(BoardEntity board) throws Exception {
        boardRepository.save(board);
    }

//    게시글 수정
    @Override
    public void updateBoard(BoardEntity board) throws Exception {
        boardRepository.save(board);
    }

//    게시판 검색
    @Override
    public Optional<BoardEntity> findAllByTitle(String searchString) throws Exception {
        Optional<BoardEntity> boardList = boardRepository.findAllByTitle(searchString);
        return boardList;
    }

//    게시판 카테고리 검색
    @Override
    public List<BoardEntity> searchCateListBoard(Long boardId, String cate) throws Exception {
        List<BoardEntity> boardList = boardRepository.findAllByCategory(cate);
        return boardList;
    }

    @Override
    public List<BoardEntity> findAllByUserId(Long userId) throws Exception {
        return boardRepository.findAllByUser_Id(userId);
    }

    @Override
    public List<UserEntity> findAllByUserBoardLikeList(Long userId) throws Exception {
        return List.of();
    }

    @Override
    public List<BoardLikeEntity> findAllByUserBoardLikeList(List<BoardLikeEntity> boardLikeList) {
        return List.of();
    }


//    @Override
//    public List<UserEntity> findAllByUserBoardLikeList(Long userId) throws Exception {
//        UserEntity user = userRepository.findById(userId);
//
//        BoardLikeEntity like = new BoardLikeEntity();
//        like.setLikeYn(like.getLikeYn());
//        boardLikeRepository.save(like);
//
//        return null;
//    }
//
//    @Override
//    public List<UserEntity> findById(Long userId) {
//        BoardLikeEntity like = boardLikeRepository.findById(userId);
//
//        boardLikeRepository.deletedByLikeYn(userId);
//    }
//
//    public void setBoardLikeRepository(Long boardId) {
//        return boardLikeRepository.countByLikeYn(BoardLikeEntity);
//    }
}
