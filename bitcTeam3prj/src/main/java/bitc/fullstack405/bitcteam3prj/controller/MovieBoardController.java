
package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.*;
import bitc.fullstack405.bitcteam3prj.database.repository.MovieRatingRepository;
import bitc.fullstack405.bitcteam3prj.service.*;
import jakarta.servlet.http.HttpSession;
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
  @Autowired
  private MovieLikeService movieLikeService;


    @GetMapping({"/", ""})
    public ModelAndView getMovieBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("/movie/movieList");

        var movieBoardList = movieBoardService.selectMovieBoardList();
        mv.addObject("movieBoardList", movieBoardList);

        return mv;
    }

    @GetMapping("/movieinfo/{movieBoardId}")
    public ModelAndView getMovieBoardDetail(
            @PathVariable("movieBoardId") long id,
            HttpSession session)  throws Exception {

        ModelAndView mv = new ModelAndView();

        MovieBoardEntity entity = movieBoardService.findByMovieId(id);
        entity.setViewCnt(entity.getViewCnt() + 1);

        movieBoardService.update(entity);

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

        boolean isLike = false;

        String userId = (String)session.getAttribute("userId");

        if(userId != null) {
          UserEntity user = userService.findByUserId((String) session.getAttribute("userId"));
          int likeCnt = movieLikeService.getMovieLikeCnt(user.getId(), entity.getMovie().getId());
          if(likeCnt > 0) {isLike = true;}
        }




        mv.addObject("movie", entity);
        mv.addObject("ratingList", ratingList);
        mv.addObject("isLike", isLike);

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
