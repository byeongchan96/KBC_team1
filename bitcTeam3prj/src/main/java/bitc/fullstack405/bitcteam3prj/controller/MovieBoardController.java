package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.*;
import bitc.fullstack405.bitcteam3prj.service.MovieBoardService;
import bitc.fullstack405.bitcteam3prj.service.MovieService;
import bitc.fullstack405.bitcteam3prj.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/movie")
public class MovieBoardController {

    @Autowired
    private MovieBoardService movieBoardService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RatingService ratingService;


    @GetMapping("/")
    public ModelAndView getMovieBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("");

        var movieBoardList = movieBoardService.selectMovieBoardList();
        mv.addObject("movieBoardList", movieBoardList);

        return mv;
    }

///{movieBoardId}
//        ModelAndView mv = new ModelAndView("");
//
//        MovieBoardEntity movieBoard = movieBoardService.selectMovieBoardDetail(movieBoardId);
//        mv.addObject(movieBoard);
//
//        return mv;
    @GetMapping("/movieinfo/{movieId}")
    public ModelAndView getMovieBoardDetail(
            @PathVariable("movieId") long id) throws Exception {

        ModelAndView mv = new ModelAndView();

        MovieEntity entity = movieService.selectMovieById(id);
        mv.addObject("movieId", entity.getId());
        mv.addObject("directorId", entity.getDirector());
        mv.addObject("movieCate", entity.getMovieCate());
        mv.addObject("posterImg", entity.getPosterImg());
        mv.addObject("movieName", entity.getMovieName());
        mv.addObject("openDt", entity.getOpenDt());
        mv.addObject("showTm", entity.getShowTm());
        mv.addObject("movieDisc", entity.getMovieDisc());
        mv.addObject("company", entity.getCompany());
        mv.addObject("grade", entity.getGrade());





        mv.setViewName("/movie/movieInfo");
        return mv;
    }

    @PostMapping("/{movieBoardId}/rating")
    public String writeMovieRating(
            @PathVariable("movieBoardId") long movieBoardId,
            MovieBoardRatingEntity ratingEntity) throws Exception {


        return "redirect:" + movieBoardId;
    }


    @ResponseBody
    @DeleteMapping("/{movieBoardId}/rating/{ratingId}")
    public Object deleteMovieRating(
            @PathVariable("movieBoardId") long movieBoardId,
            @PathVariable("ratingId") long ratingId) throws Exception {

        return null;
    }

}