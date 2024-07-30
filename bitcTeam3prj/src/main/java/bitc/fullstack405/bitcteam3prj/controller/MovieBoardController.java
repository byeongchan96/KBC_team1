
package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.*;
import bitc.fullstack405.bitcteam3prj.database.repository.MovieRatingRepository;
import bitc.fullstack405.bitcteam3prj.service.MovieBoardService;
import bitc.fullstack405.bitcteam3prj.service.MovieService;
import bitc.fullstack405.bitcteam3prj.service.PaginationService;
import bitc.fullstack405.bitcteam3prj.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private PaginationService paginationService;


    @GetMapping({"/", ""})
    public ModelAndView getMovieBoardList(
            @PageableDefault(page=0, size=10, sort = "id") Pageable pageable,
            @RequestParam(required = false) String searchCate,
            @RequestParam(required = false) String searchTitle
    ) throws Exception {
        ModelAndView mv = new ModelAndView("/movie/movieList");

        Page<MovieBoardEntity> movieBoardList;

        boolean searchCateChk = searchCate == null || searchCate.isEmpty();
        boolean searchTitleChk = searchTitle == null || searchTitle.isEmpty();
        
        if(searchCateChk && searchTitleChk){
            movieBoardList = movieBoardService.selectMovieBoardList(pageable);
        }
        else if (searchTitleChk){
            movieBoardList = movieBoardService.selectMovieBoardListByCate(pageable);
        }
        else if(searchCateChk){
            movieBoardList = movieBoardService.selectMovieBoardListByTitle(pageable, searchTitle);
        }
        else{
            movieBoardList = movieBoardService.selectMovieBoardListByCateAndTitle(pageable);
        }

//        var movieBoardList = movieBoardService.selectMovieBoardList(pageable);


        mv.addObject("movieBoardList", movieBoardList);
        mv.addObject(
                "barList",
                paginationService.getPaginationBarNumbers(
                        pageable.getPageNumber(),
                        movieBoardList.getTotalPages()
                ));

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


    @PostMapping("/{movieBoardId}/rating")
    public String writeMovieRating(@PathVariable("movieBoardId") long movieBoardId, MovieBoardRatingEntity ratingEntity) throws Exception {

        ratingEntity.setMovieBoard(movieBoardService.selectMovieBoardDetail(movieBoardId));
//        ratingEntity.setMovieRating(movieRating);
//        ratingEntity.setContent(content);

        ratingService.insertRating(ratingEntity);
        ;

        return "redirect:/movie/movieinfo/{movieBoardId}";
    }

//    @PostMapping("/{movieBoardId}/rating")
//    public String writeRating(@PathVariable("movieBoardId") long movieBoardId, @RequestParam("movieRating") int movieRating, @RequestParam("content") String content) throws Exception {
//
//        MovieBoardRatingEntity entity = new MovieBoardRatingEntity();
//
//        entity.setMovieBoard((MovieBoardEntity)movieBoardId);
//
//        ratingService.insert();
//
//    }


    @ResponseBody
    @DeleteMapping("/{movieBoardId}/rating/{ratingId}")
    public Object deleteMovieRating(
            @PathVariable("movieBoardId") long movieBoardId,
            @PathVariable("ratingId") long ratingId) throws Exception {


        return null;
    }

}
