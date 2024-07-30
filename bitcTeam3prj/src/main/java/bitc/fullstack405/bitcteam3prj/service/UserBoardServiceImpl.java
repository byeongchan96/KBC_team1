package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import bitc.fullstack405.bitcteam3prj.database.repository.BoardRepository;
import bitc.fullstack405.bitcteam3prj.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBoardServiceImpl implements UserBoardService{

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

//    유저가 작성한 게시글 리스트
    @Override
    public List<BoardEntity> userBoardList(Long userId) throws Exception {
//        List<BoardEntity> boardList =
        return List.of();
    }

//    유저가 비/추천한 게시글 리스트
    @Override
    public List<BoardEntity> userLikeBoardList(Long userId) throws Exception {
        return List.of();
    }

//    유저가 북마크한 영화 리스트
    @Override
    public List<BoardEntity> movieBookmarkList(Long userId) throws Exception {
        return List.of();
    }
}
