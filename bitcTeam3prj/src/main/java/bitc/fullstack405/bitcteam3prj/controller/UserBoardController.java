package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardEntity;
import bitc.fullstack405.bitcteam3prj.service.BoardService;
import bitc.fullstack405.bitcteam3prj.service.UserBoardService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserBoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserBoardService userBoardService;

    //    유저가 작성한 게시글 리스트
    @GetMapping("/{userId}")
    public ModelAndView userBoardList(@PathVariable("userId") Long userId) throws Exception {
        ModelAndView mv = new ModelAndView("/board/");
        List<BoardEntity> boardList = userBoardService.userBoardList(userId);
        mv.addObject("boardList" , boardList);

        return mv;
    }

    //    유저가 비/추천한 게시글 리스트
    @GetMapping("/boardLike/{userId}")
    public ModelAndView userLikeBoardList(@PathVariable("userId") Long userId) throws Exception {
        ModelAndView mv = new ModelAndView("/board/");
        List<BoardEntity> boardList = userBoardService.userLikeBoardList(userId);
        mv.addObject("boardList" , boardList);

        return mv;
    }

    //    유저가 북마크한 영화 리스트
    @GetMapping("/boardMovieBookmark/{userId}")
    public ModelAndView movieBookmarkBoardList(@PathVariable("userId") Long userId) throws Exception {
        ModelAndView mv = new ModelAndView("/board/");
        List<BoardEntity> boardList = userBoardService.movieBookmarkList(userId);
        mv.addObject("boardList" , boardList);

        return mv;
    }
}

