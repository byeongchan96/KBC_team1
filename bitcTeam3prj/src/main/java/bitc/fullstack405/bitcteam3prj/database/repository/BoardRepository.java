package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    BoardEntity findById(long id);
    Optional<BoardEntity> findAllByTitle(String title);
    Optional<BoardEntity> findByVisitCnt(long VisitCnt);
    Optional<BoardEntity> findByContent(String Content);
    List<BoardEntity> findAllByCategory(String Category);
    Optional<BoardEntity> findAllByWarning(String Warning);

    BoardEntity queryByTitle(String title);

    boolean existsByCategory(String Category);

    int countByTitle(String title);
    int countByCategory(String Category);
    int countByContent(String Content);

    void deleteByTitle(String title);
    void deleteByVisitCnt(int visitCnt);
    void deleteByContent(String content);
    void deleteByCategory(String category);
    void deleteByWarning(String warning);

    List<BoardEntity> findFirst5ByTitle(String title);
    List<BoardEntity> findTop5ByVisitCnt(int visitCnt);

    BoardEntity findByTitleIs(String title);
    BoardEntity findByCategoryIs(String Category);

    List<BoardEntity> findByTitleLike(String title);
    List<BoardEntity> findByCategoryLike(String Category);

}
