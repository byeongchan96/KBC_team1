package bitc.fullstack405.bitcteam3prj.service;

public interface RatingService {
  void findAllByMovieBoard() throws Exception;
  void deleteByUser() throws Exception;
  void querySelectAvgRating() throws Exception;
}
