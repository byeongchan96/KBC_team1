package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardRatingEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.MovieEntity;

import java.util.List;

public interface RatingService {
  List<MovieBoardRatingEntity> findAllByMovieBoard(MovieBoardEntity movieBoard) throws Exception;
  void deleteByUser() throws Exception;
  void queryAvgRating() throws Exception;
}
