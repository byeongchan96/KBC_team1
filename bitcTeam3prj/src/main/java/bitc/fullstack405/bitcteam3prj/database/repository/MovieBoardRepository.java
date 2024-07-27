package bitc.fullstack405.bitcteam3prj.database.repository;


import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieBoardRepository extends JpaRepository<MovieBoardEntity, Long> {

  MovieBoardEntity findById(long id) throws Exception;

}