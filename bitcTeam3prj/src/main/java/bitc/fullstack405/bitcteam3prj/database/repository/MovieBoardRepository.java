package bitc.fullstack405.bitcteam3prj.database.repository;


import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MovieBoardRepository extends JpaRepository<MovieBoardEntity, Long> {

  MovieBoardEntity findById(long id) throws Exception;


    Page<MovieBoardEntity> findAllByMovie_MovieNameContains(Pageable pageable, String searchTitle);
}