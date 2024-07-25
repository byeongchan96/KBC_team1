package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.dto.movie.*;
import bitc.fullstack405.bitcteam3prj.database.entity.DirectorEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.MovieEntity;
import bitc.fullstack405.bitcteam3prj.service.DirectorService;
import bitc.fullstack405.bitcteam3prj.service.ImgFileService;
import bitc.fullstack405.bitcteam3prj.service.MovieService;
import bitc.fullstack405.bitcteam3prj.service.ParserService;
import bitc.fullstack405.bitcteam3prj.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ParserController {
    @Autowired
    private ParserService parserService;

    @Autowired
    private DirectorService directorService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ImgFileService imgFileService;

    @Value("${team3.movie.url}")
    private String movieURL;

    @Value("${team3.movie.service.key}")
    private String movieKey;

    @Autowired
    private FileUtils fileUtils;

    @RequestMapping("/insert/director")
    public ModelAndView setDirector() throws Exception {
        String serviceURL = movieURL + "&ServiceKey=" + movieKey;

        MovieDTO movieData = parserService.getMovieData(serviceURL);

        ModelAndView mv = new ModelAndView("/board/testBoard");
        mv.addObject("movieData", movieData);

        List<String> directorNmList = new ArrayList<>();
        List<DirectorEntity> directorEntities = new ArrayList<>();

        directorNmList.add("감독 미상");
        var noNamedDirector = new DirectorEntity();
        noNamedDirector.setName("감독 미상");
        directorEntities.add(noNamedDirector);

        var dataList = movieData.getData();
        for (DataDTO data : dataList) {
            var ResultList = data.getResult();
            for (ResultDTO result : ResultList) {
                var directorList = result.getDirectors().getDirector();
                for (DirectorDTO director : directorList) {
                    String directorNm = director.getDirectorNm();
                    if (directorNm.isEmpty()) {
                        directorNm = director.getDirectorEnNm();
                        if (directorNm.isEmpty()) {
                            directorNm = "감독 미상";
                        }
                    }

                    if(directorNmList.contains(directorNm) == false) {
                        DirectorEntity directorEntity = new DirectorEntity();
                        directorNmList.add(directorNm);
                        directorEntity.setName(directorNm);

                        directorEntities.add(directorEntity);
                    }
                    break;
                }
            }
        }

        if (directorEntities.size() > 0) {
            directorService.saveAllDirector(directorEntities);
        }

        return mv;
    }


    @RequestMapping("/insert/movie")
    public String setMovie() throws Exception {
        String serviceURL = movieURL + "&ServiceKey=" + movieKey;

        MovieDTO movieData = parserService.getMovieData(serviceURL);

        List<MovieEntity> movieEntities = new ArrayList<>();
        var dataList = movieData.getData();
        for(var data : dataList) {
            var ResultList = data.getResult();
            for (ResultDTO result : ResultList) {
                var directorList = result.getDirectors().getDirector();
                for (DirectorDTO director : directorList) {
                    String directorNm = director.getDirectorNm();
                    if (directorNm.isEmpty()) {
                        directorNm = director.getDirectorEnNm();
                        if (directorNm.isEmpty()) {
                            directorNm = "감독 미상";
                        }
                    }


                    MovieEntity movieEntity = new MovieEntity();
//                    movieEntity.setDirector(directorEntity);
                    movieEntity.setMovieName(result.getTitle());
                    movieEntity.setCompany(result.getCompany());
                    movieEntity.setMovieCate(result.getGenre());
                    movieEntity.setGrade(result.getRating());
                    movieEntity.setMovieDisc(result.getPlots().getPlot().get(0).getPlotText());

                    System.out.println("-------------------------------------");
                    System.out.println(result.getPlots().getPlot().get(0).getPlotText());
                    System.out.println("-------------------------------------");

                    int runTime = 0;

                    if(!result.getRuntime().isEmpty()){
                        runTime = Integer.parseInt(result.getRuntime());
                    }
                    movieEntity.setShowTm(runTime);
//                    movieEntity.setPosterImg();


                    movieEntities.add(movieEntity);
                    break;
                }
            }
        }

        movieService.saveAllMovie(movieEntities);

        return "/board/testBoard";
    }

    @RequestMapping("/insert/imgFile")
    public String insertImgFile() throws Exception{
        String serviceURL = movieURL + "&ServiceKey=" + movieKey;
        MovieDTO movieData = parserService.getMovieData(serviceURL);

        List<ImgFileEntity> imgFileEntities = new ArrayList<>();
        var dataList = movieData.getData();
        for(var data : dataList) {
            var ResultList = data.getResult();
            for (ResultDTO result : ResultList) {
                String posters = result.getPosters();
                if(posters.isEmpty()){
                    continue;
                }
                String[] posterURLs = result.getPosters().split("\\|");
                fileUtils.UrlToImage( posterURLs[0], result.getTitle());

                ImgFileEntity imgFileEntity = new ImgFileEntity();
                imgFileEntity.setOriName(posterURLs[0]);
                imgFileEntity.setSavedName(result.getTitle());

                imgFileEntities.add(imgFileEntity);
            }
        }

        imgFileService.saveAllImageFile(imgFileEntities);
        return "/board/testBoard";
    }

    @RequestMapping("/insert/dummy")
    public String setDummy() throws Exception{
        String serviceURL = movieURL + "&ServiceKey=" + movieKey;
        MovieDTO movieData = parserService.getMovieData(serviceURL);


        List<MovieEntity> movieEntities = new ArrayList<>();

        List<ImgFileEntity> imgFileEntities = new ArrayList<>();

        var dataList = movieData.getData();
        var data = dataList.get(0);
        var resultList = data.getResult();
        for(var result : resultList){
            String genre = result.getGenre();
            if(genre.contains("에로")){
                continue;
            }
            int runTime = 0;
            if(!result.getRuntime().isEmpty()){
                runTime = Integer.parseInt(result.getRuntime());
            }

            if(runTime == 0){
                continue;
            }

            DirectorDTO director = result.getDirectors().getDirector().get(0);
            String directorNm = director.getDirectorNm();
            if (directorNm.isEmpty()) {
                directorNm = director.getDirectorEnNm();
                if (directorNm.isEmpty()) {
                    directorNm = "감독 미상";
                }
            }
            var directorEntity = directorService.getDirectorByName(directorNm);

            //////////////////////////

            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setDirector(directorEntity);
            movieEntity.setMovieName(result.getTitle());
            movieEntity.setCompany(result.getCompany());
            movieEntity.setMovieCate(result.getGenre());
            movieEntity.setGrade(result.getRating());
            movieEntity.setMovieDisc(result.getPlots().getPlot().get(0).getPlotText());
            movieEntity.setShowTm(runTime);

            movieEntities.add(movieEntity);


            //////////////////////////////
            String posters = result.getPosters();
            if(posters.isEmpty()){
                continue;
            }
            String[] posterURLs = result.getPosters().split("\\|");
            try {
                fileUtils.UrlToImage(posterURLs[0], result.getTitle());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            ImgFileEntity imgFileEntity = new ImgFileEntity();
            imgFileEntity.setSavedPath("C:/UrlToImage/");
            imgFileEntity.setOriName(posterURLs[0]);
            imgFileEntity.setSavedName(result.getTitle());
            imgFileEntity.setMovie(movieEntity);

            imgFileEntities.add(imgFileEntity);
        }

        movieService.saveAllMovie(movieEntities);
        imgFileService.saveAllImageFile(imgFileEntities);

        return "/board/testBoard";
    }
}
