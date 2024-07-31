package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;

import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    BoardEntity findById(long id);
    Optional<BoardEntity> findAllByTitle(String title);
    List<BoardEntity> findAllByCategory(String Category);

    List<BoardEntity> findAllByUser_Id(long id);

//    List<UserEntity> findAllByUser_BoardLikeList(String MovieCate);

//    @Query("SELECT b FROM BoardEntity AS b WHERE b.title LIKE '%?1%'")
//    List<BoardEntity> findAllBySearch(String search);
//
//    @Query("SELECT b FROM BoardEntity AS b WHERE b.category = :category ")
//    BoardEntity findAllByCategory(@Param("boardEntity") BoardEntity boardEntity);
//
//    @Query("SELECT b FROM UserEntity AS b WHERE b.userId = :userId ")
//    UserEntity findAllByUser(@Param("userEntity") UserEntity userEntity);
//
//    @Query("SELECT b FROM BoardEntity AS b WHERE b.visitCnt = :visitCnt ")
//    BoardEntity findAllByVisitCnt(@Param("boardEntity") BoardEntity boardEntity);
//
//    @Query("SELECT b FROM BoardEntity AS b WHERE b.warning = :warning ")
//    BoardEntity findAllByWarning(@Param("boardEntity") BoardEntity boardEntity);

}
