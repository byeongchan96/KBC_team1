package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"", "/", "index"})
public class TestController {

  @Autowired
  private UserService userService;


//  로그인 화면 뷰
  @RequestMapping("/login.do")
  public String login() throws Exception{
    return "/user/logInTest";
  }


//  로그인 프로세스
  @RequestMapping("/loginProcess.do")
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
}
