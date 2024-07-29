
package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.*;
import bitc.fullstack405.bitcteam3prj.database.repository.MovieRatingRepository;
import bitc.fullstack405.bitcteam3prj.service.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/movie")
public class MovieBoardController {

    @Autowired
    private MovieBoardService movieBoardService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RatingService ratingService;
    @Autowired
    private MovieRatingRepository movieRatingRepository;
  @Autowired
  private UserService userService;


    @GetMapping({"/", ""})
    public ModelAndView getMovieBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("/movie/movieList");

        var movieBoardList = movieBoardService.selectMovieBoardList();
        mv.addObject("movieBoardList", movieBoardList);

        return mv;
    }

    @GetMapping("/movieinfo/{movieBoardId}")
    public ModelAndView getMovieBoardDetail(
            @PathVariable("movieBoardId") long id) throws Exception {

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

            avg = (float) sum / size;
            mv.addObject("avg", avg);
        }

        mv.addObject("movie", entity);
        mv.addObject("ratingList", ratingList);

        MovieEntity movieEntity = movieService.selectMovieById(id);

        mv.setViewName("/movie/movieInfo");
        return mv;
    }

    @GetMapping("/{movieBoardId}/rating")
    public String writeMovieRating() throws Exception {
        return "/movie/movieinfo/{movieBoardId}";
    }

    @PostMapping("/{movieBoardId}/rating/{userId}")
    public String writeMovieRating(
            @PathVariable("userId") String userId,
            @PathVariable("movieBoardId") long movieBoardId,
            MovieBoardRatingEntity ratingEntity) throws Exception {

        var user = userService.findByUserId(userId);
        ratingEntity.setMovieBoard(movieBoardService.selectMovieBoardDetail(movieBoardId));
        ratingEntity.setUser(user);

        ratingService.insertRating(ratingEntity);

        return "redirect:/movie/movieinfo/" + movieBoardId;
    }


  @PostMapping("/movieinfo/update/{movieBoardRatingId}")
  public String updateMovieRating(
          @PathVariable("movieBoardRatingId") long movieBoardRatingId,
                        MovieBoardRatingEntity ratingEntity,
                        long movieBoardId, String userId) throws Exception {

    ratingEntity.setId(movieBoardRatingId);

    MovieBoardEntity movieBoardEntity = movieBoardService.selectMovieBoardDetail(movieBoardId);
    UserEntity userEntity = userService.findByUserId(userId);
    ratingEntity.setMovieBoard(movieBoardEntity);
    ratingEntity.setUser(userEntity);

    ratingService.updateRating(ratingEntity);

    return "redirect:/movie/movieinfo/" + movieBoardId;
  }

    @PostMapping("/movieinfo/delete/{movieBoardRatingId}")
    public String deleteMovieRating(
            @PathVariable("movieBoardRatingId") long movieBoardRatingId,
            long movieBoardId) throws Exception {

            ratingService.deleteById(movieBoardRatingId);

        return "redirect:/movie/movieinfo/" + movieBoardId;
    }


}
