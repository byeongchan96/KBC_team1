package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardEntity;

import java.util.List;

public interface MovieBoardService {
    List<MovieBoardEntity> selectMovieBoardList() throws Exception;

    MovieBoardEntity selectMovieBoardDetail(Long movieBoardId) throws Exception;

    void insertMovieBoard(MovieBoardEntity movieBoard) throws Exception;

    void insertMovieBoardList(List<MovieBoardEntity> movieBoardList) throws Exception;
}
