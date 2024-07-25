package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import bitc.fullstack405.bitcteam3prj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@RestController
@RequestMapping({"", "/", "movcom"})
public class TestController {

  @Autowired
  private UserService userService;

  //  로그인 화면 뷰
  @RequestMapping(value = "/login")
  public ModelAndView login() throws Exception{
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/user/logInTest");

    return mv;
  }


//  로그인 프로세스
  @PostMapping("/login")
  public ModelAndView loginProcess(@RequestParam("userId")String userId, @RequestParam("userPw") String userPw, HttpServletRequest req) throws Exception{

    ModelAndView mv = new ModelAndView();


    int result = userService.isUserInfo(userId, userPw);

    if (result == 1) {

      HttpSession session = req.getSession();

      session.setAttribute("userId", userId);
      session.setAttribute("userPw", userPw);

      session.setMaxInactiveInterval(60 * 60 * 1);

      mv.setViewName("redirect:/loginSuccess");

    }
    else {
      mv.setViewName("redirect:/logInTest?error=loginFailed");
    }

    return mv;
  }


//  로그인 성공 뷰
  @RequestMapping("/loginSuccess")
  public ModelAndView loginSuccess(HttpServletRequest req) throws Exception{
    Object userId = req.getSession().getAttribute("userId");
    Object userPw = req.getSession().getAttribute("userPw");
    ModelAndView mv = new ModelAndView();
    mv.addObject("userId", userId);
    mv.addObject("userPw", userPw);

    if (req.getSession().getAttribute("userId") == null) {
      mv.setViewName("redirect:/login");
    }
    else {
      mv.setViewName("user/logInSuccessTest");
    }

    return mv;
  }


//  로그아웃 프로세스
  @RequestMapping("/logout")
  public ModelAndView logout(HttpServletRequest req) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:/login");
    HttpSession session = req.getSession();

    session.removeAttribute("userId");
    session.removeAttribute("userPw");

    session.invalidate();

    return mv;
  }

//  회원가입 뷰 페이지
  @GetMapping("/signIn")
  public ModelAndView signIn() throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("/user/signInTest");
    return mv;
  }

//  화원가입 프로세스
  @PostMapping("/signIn")
  public ModelAndView signInProcess(UserEntity userEntity, @RequestParam("userPwChk") String userPwChk, @RequestParam("userId") String userId, @RequestParam("email") String email) throws Exception {

    ModelAndView mv = new ModelAndView();

    if (Objects.equals(userPwChk, userEntity.getUserPw())) {
      if (userService.userIdCheck(userId) == 0) {
        if (userService.userEmailCheck(email) == 0) {
          userService.insertUser(userEntity);
        }
        else {
          mv.setViewName("redirect:/signIn?error=existEmail");
        }
      }
      else {
        mv.setViewName("redirect:/signIn?error=existId");
      }
    }
    else {
      mv.setViewName("redirect:/signIn?error=pwChk");
    }

    return mv;
  }
}
