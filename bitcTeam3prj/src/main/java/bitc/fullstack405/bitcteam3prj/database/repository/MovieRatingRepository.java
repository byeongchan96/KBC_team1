package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardRatingEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRatingRepository extends JpaRepository<MovieBoardRatingEntity, Long> {

  List<MovieBoardRatingEntity> findAllByMovieBoard(MovieBoardEntity movieBoard);

  void deleteByUser(UserEntity user);

  
}
