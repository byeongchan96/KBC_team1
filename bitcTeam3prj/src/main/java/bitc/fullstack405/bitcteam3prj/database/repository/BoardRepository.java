package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {


    BoardEntity findById(long id);
    Optional<BoardEntity> findAllByTitle(String title);
    List<BoardEntity> findAllByCategory(String Category);


    @Query("SELECT b FROM BoardEntity AS b WHERE b.title LIKE '%?1%'")
    List<BoardEntity> findAllBySearch(String search);
    List<BoardEntity> findAllByUser_Id(long id);

}
