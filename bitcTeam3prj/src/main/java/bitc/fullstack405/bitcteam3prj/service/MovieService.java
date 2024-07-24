package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.MovieEntity;

import java.util.List;

public interface MovieService {
    void saveAllMovie(List<MovieEntity> movieEntities) throws Exception;
}
