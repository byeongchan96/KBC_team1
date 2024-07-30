package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserBoardService {

    List<BoardEntity> userBoardList(Long userId) throws Exception;

    List<BoardEntity> userLikeBoardList(Long userId) throws Exception;

    List<BoardEntity> movieBookmarkList(Long userId) throws Exception;
}
