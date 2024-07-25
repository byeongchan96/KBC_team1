package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.ManagerEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.MovieBoardRatingEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.MovieEntity;
import bitc.fullstack405.bitcteam3prj.service.ManagerService;
import bitc.fullstack405.bitcteam3prj.service.MovieBoardService;
import bitc.fullstack405.bitcteam3prj.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movie")
public class MovieBoardController {

    @Autowired
    private MovieBoardService movieBoardService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ManagerService managerService;


    @GetMapping("/")
    public ModelAndView getMovieBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("");

        var movieBoardList = movieBoardService.selectMovieBoardList();
        mv.addObject("movieBoardList", movieBoardList);

        return mv;
    }

    @PostMapping("/")
    public String writeMovieBoard() throws Exception{
        ManagerEntity manager = managerService.selectManagerById(1L);

        List<MovieEntity> movieList = movieService.selectMovieList();

        List<MovieBoardEntity> movieBoardList = new ArrayList<>();
        MovieBoardEntity movieBoard = null;
        for(var movie : movieList){
            movieBoard = new MovieBoardEntity();
            movieBoard.setMovie(movie);
            movieBoard.setManager(manager);

            movieBoard.setViewCnt(0);
            movieBoardList.add(movieBoard);
        }

        movieBoardService.insertMovieBoardList(movieBoardList);

        return "redirect:/board/testBoard";
    }

    @GetMapping("/{movieBoardId}")
    public ModelAndView getMovieBoardDetail(
            @PathVariable("movieBoardId") long movieBoardId) throws Exception {

        ModelAndView mv = new ModelAndView("");

        MovieBoardEntity movieBoard = movieBoardService.selectMovieBoardDetail(movieBoardId);
        mv.addObject(movieBoard);

        return mv;
    }

    @PostMapping("/{movieBoardId}/rating")
    public String writeMovieRating(
            @PathVariable("movieBoardId") long movieBoardId,
            MovieBoardRatingEntity ratingEntity) throws Exception {


        return "redirect:/";
    }


    @ResponseBody
    @DeleteMapping("/{movieBoardId}/rating/{ratingId}")
    public Object deleteMovieRating(
            @PathVariable("movieBoardId") long movieBoardId,
            @PathVariable("ratingId") long ratingId) throws Exception {

        return null;
    }
}