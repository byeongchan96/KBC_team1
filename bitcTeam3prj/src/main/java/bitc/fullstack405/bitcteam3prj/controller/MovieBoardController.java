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

        MovieBoardEntity entity = movieBoardService.findByMovieId(id);
        var ratingList = ratingService.findAllByMovieBoard(entity);


        float avg = 0.0f;
        int size = ratingList.size();
        if(size > 0) {
            int sum = 0;
            for (var rating : ratingList) {
                sum += rating.getMovieRating();
            }

            avg = sum / size;
            mv.addObject("avg", avg);
        }


        mv.addObject("movie", entity);
        mv.addObject("ratingList", ratingList);

        MovieEntity movieEntity = movieService.selectMovieById(id);

//        mv.addObject("movieId", movieEntity.getId());
//        mv.addObject("directorId", movieEntity.getDirector());
//        mv.addObject("movieCate", movieEntity.getMovieCate());
//        mv.addObject("posterImg", movieEntity.getPosterImg());
//        mv.addObject("movieName", movieEntity.getMovieName());
//        mv.addObject("openDt", movieEntity.getOpenDt());
//        mv.addObject("showTm", movieEntity.getShowTm());
//        mv.addObject("movieDisc", movieEntity.getMovieDisc());
//        mv.addObject("company", movieEntity.getCompany());
//        mv.addObject("grade", movieEntity.getGrade());



//        MovieBoardEntity movieBoardEntity = movieBoardService.findByMovieId(entity.getId());
//
//        mv.addObject("movieBoardId", movieBoardEntity.getId());
//        mv.addObject("movieBoard")
//
//
//
//
//        MovieBoardRatingEntity ratingEntity = ratingService.findAllByMovieBoard(id);


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