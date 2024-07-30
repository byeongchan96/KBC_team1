package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.*;
import bitc.fullstack405.bitcteam3prj.database.repository.MovieLikeRepository;
import bitc.fullstack405.bitcteam3prj.service.*;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserBoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieLikeService movieLikeService;

    @Autowired
    private UserService userService;
  @Autowired
  private MovieLikeRepository movieLikeRepository;


    //    유저가 작성한 게시글 리스트
    @GetMapping("/{userId}")
    public ModelAndView userBoardList(@PathVariable("userId") Long userId) throws Exception {
        ModelAndView mv = new ModelAndView("/board/");
        List<BoardEntity> boardList = boardService.userBoardList(userId);
        mv.addObject("boardList" , boardList);

        return mv;
    }

    //    유저가 비/추천한 게시글 리스트
    @GetMapping("/boardLike/{userId}")
    public ModelAndView userLikeBoardList(@PathVariable("userId") Long userId) throws Exception {
        ModelAndView mv = new ModelAndView("/board/");
        List<BoardEntity> boardList = boardService.userLikeBoardList(userId);
        mv.addObject("boardList" , boardList);

        return mv;
    }

    //    유저가 북마크한 영화 리스트
    @GetMapping("/boardMovieBookmark/{userId}")
    public ModelAndView movieBookmarkBoardList(
            @PathVariable("userId") String userId) throws Exception {

        ModelAndView mv = new ModelAndView();
        UserEntity entity = userService.findByUserId(userId);



        var movieLikeList = movieLikeService.findByUser(entity);

        List<MovieEntity> movieList = new ArrayList<>();

        for(MovieLikeEntity movieLike : movieLikeList){
            MovieEntity movie = movieService.selectMovieById(movieLike.getMovie().getId());
            movieList.add(movie);
        }

//        List<BoardEntity> boardList = boardService.movieBookmarkList(userId);
        mv.addObject("movieList",movieList);
        return mv;
    }


  @PostMapping("/boardMovieBookmark/{userId}/{movieId}")
  public String addMovieBookmark(
          @PathVariable("userId") String userId,
          @PathVariable("movieId") long movieId,
          MovieLikeEntity movieLikeEntity,
          boolean bookMark) throws Exception {

    var user = userService.findByUserId(userId);
    movieLikeEntity.setMovie(movieService.selectMovieById(movieId));
    movieLikeEntity.setUser(user);

    if (bookMark) {
      movieLikeService.addLike(movieLikeEntity);
    } else {
      movieLikeService.deleteLike(user.getId(), movieId);
    }

    return "redirect:/movie/movieinfo/" + movieId;
  }

}

