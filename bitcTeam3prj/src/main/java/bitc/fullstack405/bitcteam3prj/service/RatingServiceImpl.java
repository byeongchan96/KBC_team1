package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.repository.MovieRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

  @Autowired
  private MovieRatingRepository movieRatingRepo;

  @Override
  public void findAllByMovieBoard() throws Exception {
    
  }

  @Override
  public void deleteByUser() throws Exception {

  }

  @Override
  public void queryAvgRating() throws Exception {

  }
}
