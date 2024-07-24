package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import bitc.fullstack405.bitcteam3prj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping({"", "/", "index"})
public class TestController {

  @Autowired
  private UserService userService;

  //  로그인 화면 뷰
  @GetMapping("/login.do")
  public String login() throws Exception{
    return "/user/logInTest";
  }


//  로그인 프로세스
  @PostMapping("/login.do")
  public String loginProcess(@RequestParam("userId")String userId, @RequestParam("userPw") String userPw, HttpServletRequest req) throws Exception{

    int result = userService.isUserInfo(userId, userPw);

    if (result == 1) {

      HttpSession session = req.getSession();

      session.setAttribute("userId", userId);
      session.setAttribute("userPw", userPw);

      session.setMaxInactiveInterval(60 * 60 * 1);

      return "redirect:/loginSuccess.do";
    }
    else {
      return "redirect:/login.do";
    }
  }


//  로그인 성공 뷰
  @RequestMapping("/loginSuccess.do")
  public ModelAndView loginSuccess(HttpServletRequest req) throws Exception{
    Object userId = req.getSession().getAttribute("userId");
    Object userPw = req.getSession().getAttribute("userPw");
    ModelAndView mv = new ModelAndView();
    mv.addObject("userId", userId);
    mv.addObject("userPw", userPw);

    if (req.getSession().getAttribute("userId") == null) {
      mv.setViewName("redirect:/login.do");
    }
    else {
      mv.setViewName("user/logInSuccessTest");
    }

    return mv;
  }


//  로그아웃 프로세스
  @RequestMapping("/logout.do")
  public String logout(HttpServletRequest req) throws Exception {
    HttpSession session = req.getSession();

    session.removeAttribute("userId");
    session.removeAttribute("userPw");

    session.invalidate();

    return "redirect:/login.do";
  }

//  회원가입 뷰 페이지
  @GetMapping("/signIn.do")
  public String signIn() throws Exception {
    return "/user/signInTest";
  }

//  화원가입 프로세스
  @PostMapping("/signIn.do")
  public String signInProcess(UserEntity userEntity, @RequestParam("userPwChk") String userPwChk, @RequestParam("userId") String userId, @RequestParam("email") String email) throws Exception {

    if (Objects.equals(userPwChk, userEntity.getUserPw())) {
      if (userService.userIdCheck(userId) == 0) {
        if (userService.userEmailCheck(email) == 0) {
          userService.insertUser(userEntity);
        }
        else {
          return "redirect:/signIn.do?error=existEmail";
        }
      }
      else {
        return "redirect:/signIn.do?error=existId";
      }
    }
    else {
      return "redirect:/signIn.do?error=pwChk";
    }

    return "redirect:/login.do";
  }
}
