package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {


    BoardEntity findById(long id);
    Optional<BoardEntity> findAllByTitle(String title);
    List<BoardEntity> findAllByCategory(String Category);
    Optional<BoardEntity> findAllByWarning(String Warning);


    @Query("SELECT b FROM BoardEntity AS b WHERE b.title LIKE '%?1%'")
    List<BoardEntity> findAllBySearch(String search);

}
