package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.dto.movie.MovieDTO;

public interface ParserService {

    MovieDTO getMovieData(String serviceURL) throws Exception;
}
