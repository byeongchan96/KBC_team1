package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardRatingEntity;
import bitc.fullstack405.bitcteam3prj.database.repository.MovieRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovieRatingServiceImpl implements MovieRatingService {
    @Autowired
    private MovieRatingRepository ratingRepository;

    @Override
    public void insertRatingList(List<MovieBoardRatingEntity> ratingLlist) throws Exception {
        ratingRepository.saveAll(ratingLlist);
    }
}
