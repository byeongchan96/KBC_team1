package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardEntity;
import bitc.fullstack405.bitcteam3prj.database.repository.MovieBoardRepository;
import bitc.fullstack405.bitcteam3prj.database.repository.MovieLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieBoardServiceImpl implements MovieBoardService {

    @Autowired
    private MovieBoardRepository movieBoardRepository;

    @Override
    public List<MovieBoardEntity> selectMovieBoardList() throws Exception {
        return movieBoardRepository.findAll();
    }


    @Override
    public MovieBoardEntity selectMovieBoardDetail(Long movieBoardId) throws Exception {
        MovieBoardEntity movieBoard = null;
        var opt =  movieBoardRepository.findById(movieBoardId);

        if(opt.isPresent()){
            movieBoard = (MovieBoardEntity)opt.get();
        }

        return movieBoard;
    }

    @Override
    public void insertMovieBoard(MovieBoardEntity movieBoard) throws Exception {
        movieBoardRepository.save(movieBoard);
    }

    @Override
    public void insertMovieBoardList(List<MovieBoardEntity> movieBoardList) throws Exception {
        movieBoardRepository.saveAll(movieBoardList);
    }

    @Override
    public MovieBoardEntity findByMovieId(long movieId) throws Exception {

        return movieBoardRepository.findById(movieId);


    }

    @Override
    public void update(MovieBoardEntity movieBoard) throws Exception {
        movieBoardRepository.save(movieBoard);
    }


}