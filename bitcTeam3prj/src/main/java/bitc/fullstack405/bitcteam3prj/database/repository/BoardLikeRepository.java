package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardLikeEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardLikeRepository extends JpaRepository<BoardLikeEntity, Long> {

//    List<BoardLikeEntity> findByBoardIdAndUserId(long boardId, long userId);

//    List<BoardLikeEntity> findAllByUserBoardLikeList(Long userId);

    @Query("SELECT COUNT(b) FROM BoardLikeEntity b WHERE b.likeYn is null and b.user = :user")
    int countByLikeYn(@Param("user") UserEntity user);
}
