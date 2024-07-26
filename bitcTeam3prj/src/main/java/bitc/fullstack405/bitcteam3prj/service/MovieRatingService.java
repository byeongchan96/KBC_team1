package bitc.fullstack405.bitcteam3prj.service;


import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardRatingEntity;

import java.util.List;

public interface MovieRatingService {
    void insertRatingList(List<MovieBoardRatingEntity> ratingLlist) throws Exception;
}
