package bitc.fullstack405.bitcteam3prj.controller;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import bitc.fullstack405.bitcteam3prj.service.ImageService;
import bitc.fullstack405.bitcteam3prj.service.UserService;
import bitc.fullstack405.bitcteam3prj.utils.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping({"", "/", "movcom"})
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ImageService imageService;

//  테스트용 메인페이지
  @GetMapping({"/home", "/main"})
  public ModelAndView home() {
    ModelAndView mv = new ModelAndView("/main/mainHome");
    return mv;
  }

  //  로그인 화면 뷰
  @GetMapping ("/login")
  public ModelAndView  login(HttpServletRequest req) throws Exception{
    ModelAndView mv = new ModelAndView();

    boolean cookieCheck = false;

    String userId = CookieUtil.readCookie(req, "userId");

    if (!userId.isEmpty()) {
      cookieCheck = true;
    }

    mv.addObject("userId", userId);
    mv.addObject("cookieCheck", cookieCheck);

    mv.setViewName("/login/Login");

    return mv;
  }


//  로그인 프로세스
  @PostMapping("/login")
  public ModelAndView loginProcess(@RequestParam(defaultValue = "N", required = false, value = "rememberId") List<String> rememberId, @RequestParam("userId")String userId, @RequestParam("userPw") String userPw, HttpServletRequest req, HttpServletResponse resp) throws Exception {
    ModelAndView mv = new ModelAndView();

    int result = userService.isUserInfo(userId, userPw);

    UserEntity userEntity = userService.findUserIdForProfile(userId);

    if (userId != null && userPw != null && result == 1) {
      if (rememberId.get(0) != null && rememberId.get(0).equals("Y")) {
        CookieUtil.makeCookie(resp, "userId", userId, (60 * 60 * 24 * 7));
      }
      else {
        CookieUtil.deleteCookie(resp, "userId");
      }

      UserEntity user = userService.findUserIdForProfile(userId);

      HttpSession session = req.getSession();

      session.setAttribute("userId", userId);
      session.setAttribute("userPw", userPw);
      session.setAttribute("userEmail", user.getEmail());

      session.setMaxInactiveInterval(60 * 60 * 1);

      mv.setViewName("redirect:/home");
    }
    else {
      mv.setViewName("redirect:/login?error=loginFailed");
    }

    return mv;
  }


//  로그인 성공 뷰
  @GetMapping("/loginSuccess")
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
      mv.setViewName("/user/logInSuccessTest");
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
  public String signIn() throws Exception {
    return "/login/signIn";
  }

//  화원가입 프로세스
  @PostMapping("/signIn")
  public ModelAndView signInProcess(UserEntity userEntity, @RequestParam("userPwChk") String userPwChk, @RequestParam("userId") String userId, @RequestParam("email") String email) throws Exception {

    ModelAndView mv = new ModelAndView();

    UserEntity entity = userService.findByUserIdCheckSignOut(userId);

    if (Objects.equals(userPwChk, userEntity.getUserPw())) {
//      if (entity.getDeletedYn() == 'N') {
        if (userService.userIdCheck(userId) == 0) {
          if (userService.userEmailCheck(email) == 0) {
            userService.insertUser(userEntity);
            mv.setViewName("redirect:/login");
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
        mv.setViewName("redirect:/signIn?signOutUser");
      }
//    }
//    else {
//      mv.setViewName("redirect:/signIn?error=pwChk");
//    }



    return mv;
  }

//  비밀번호 찾기 뷰
  @GetMapping("/findPassword")
  public String findPasswordView() throws Exception {
    return "user/findPasswordTest";
  }

//  비밀번호 찾기 프로세스 및 변경 뷰
  @PostMapping("/findPassword")
  public ModelAndView findPassword(@RequestParam("userId") String userId, @RequestParam("email") String email) throws Exception {
    ModelAndView mv = new ModelAndView();

    UserEntity entity = userService.findPassword(userId, email);
    mv.addObject("userId", entity.getUserId());
    mv.addObject("email", entity.getEmail());

    if (Objects.equals(entity.getUserId(), userId) && Objects.equals(entity.getEmail(), email)) {
      if (entity.getDeletedYn() == 'N') {
        mv.setViewName("redirect:/changePasswordTest");
      }
      else {
        mv.setViewName("redirect:/findPassword?error=signOutUser");
      }
    }
    else {
      mv.setViewName("redirect:/findPassword?error=notFoundUser");
    }
    return mv;
  }

//  비밀번호 변경 뷰
  @PostMapping("/changePassword")
  public ModelAndView changePasswordView(@RequestParam("userId") String userId) {
    ModelAndView mv = new ModelAndView();

    mv.setViewName("user/changePasswordTest");
    mv.addObject("userId", userId);

    return mv;
  }

//  비밀번호 변경 프로세스
  @PutMapping("/changePassword")
  public ModelAndView changePassword(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, @RequestParam("userPwChk") String userPwChk) throws Exception {
    ModelAndView mv = new ModelAndView();

    UserEntity userEntity = userService.findByUserIdCheckSignOut(userId);

    if (userEntity.getDeletedYn() == 'N') {
      if (userPw.equals(userPwChk)) {
        userService.updateUserPw(userId, userPw);
        mv.setViewName("redirect:/login");
      }
      else {
        mv.setViewName("redirect:/changePassword?error=pwChk");
      }
    }
    else {
      mv.setViewName("redirect:/changePassword?signOutUser");
    }


    return mv;
  }

//  id 찾기 뷰
  @GetMapping("/findId")
  public String findIdView() throws Exception {
    return "/user/findIdTest";
  }

//  id 찾기 프로세스
  @ResponseBody
  @PostMapping("/findId")
  public ModelAndView findId(@RequestParam("email") String email, @RequestParam("userPw") String userPw) throws Exception {
    ModelAndView mv = new ModelAndView();

    UserEntity userEntity = userService.findUserId(email, userPw);


    if (userEntity.getDeletedYn() == 'N') {
      if (userEntity != null && email.equals(userEntity.getEmail()) && userPw.equals(userEntity.getUserPw())) {
        mv.addObject("userId", userEntity.getUserId());
        mv.setViewName("user/foundIdTest");
      }
      else {
        mv.setViewName("redirect:/findId?error=notFoundUser");
      }
    }
    else {
      mv.setViewName("redirect:/findId?error=signOutUser");
    }

    return mv;
  }

//  마이페이지(프로필)
  @GetMapping("profile/{userId}")
  public ModelAndView userProfile(HttpServletRequest req, @PathVariable("userId") String userId) throws Exception {
    ModelAndView mv = new ModelAndView();

    HttpSession session = req.getSession();

    UserEntity userEntity = userService.findUserIdForProfile(userId);

//    ImgFileEntity imgFileEntity = imageService.findById();

    if(session.getAttribute("userId") != null) {
      mv.addObject("user", userEntity);
      if (userEntity != null ) {
        if (userEntity.getDeletedYn() == 'N') {

//          if (userEntity.getProfileImg() != null) {
//            mv.addObject("profileImg", userEntity.getProfileImg());
//          }
//          else {
//            mv.addObject("profileImg", "noneProfileImgPath");
//          }

          if (session.getAttribute("userId").equals(userId)) { // 자신의 프로필인지 타인의 프로필인지 확인
            mv.addObject("me", true);
            mv.setViewName("/user/myProfile");
          }
          else if (!session.getAttribute("userId").equals(userId)) {
            mv.addObject("me", false);
            mv.setViewName("/user/myProfile");
          }
        }
        else if (userEntity.getDeletedYn() == 'Y') {
          mv.addObject("me", false);
          mv.addObject("signOutUserMsg", "(탈퇴한 회원)");
          mv.setViewName("/user/myProfile");
        }
      }
      else {
        mv.setViewName("redirect:/home?error=notFoundUser");
      }
    }
    else {
      mv.setViewName("redirect:/login");
    }
    return mv;
  }

//  마이페이지(비밀번호 변경)
  @PostMapping("changePasswordProfile/{userId}")
  public ModelAndView changePw(@RequestParam("userPw") String userPw, @RequestParam("changePw") String changePw, @PathVariable("userId") String userId, @RequestParam("changePwChk") String changePwChk, HttpServletRequest req) throws Exception {
    ModelAndView mv = new ModelAndView();

    HttpSession session = req.getSession();

    UserEntity userEntity = userService.findUserIdForProfile((String) session.getAttribute("userId"));

    if (session.getAttribute("userId") != null) {
      if (userEntity.getDeletedYn() == 'N') {
        if(session.getAttribute("userId").equals(userEntity.getUserId())) {
          if (changePw.equals(changePwChk)) {
            userService.updateUserPw((String)session.getAttribute("userId"), changePw);
            mv.setViewName("redirect:/profile/" + userEntity.getUserId());
          }
          else {
            mv.setViewName("redirect:/profile/" + userEntity.getUserId() + "?error=pwChk");
          }
        }
        else {
          mv.setViewName("redirect:/profile/" + userEntity.getUserId() + "?error=notYourProfile");
        }
      }
      else {
        mv.setViewName("redirect:/main?error=signOutUser");
      }
    }
    else if (session.getAttribute("userId") != null) {
      mv.setViewName("redirect:/login");
    }
    return mv;
  }

////  회원탈퇴 뷰(GET) (기본 뷰)
//  @GetMapping("/signOut")
//  public String signOutPwChk() throws Exception {
//    return "user/signOutTest";
//  }


//  마이페이지(프로필) 탈퇴 취소 시 넘어옴
  @PostMapping("/profile/{userId}")
  public ModelAndView signOutCancel(HttpServletRequest req) throws Exception {
    ModelAndView mv = new ModelAndView();
    HttpSession session = req.getSession();

    mv.setViewName("redirect:/profile/" + session.getAttribute("userId"));

    return mv;
  }


//  회원탈퇴
  @DeleteMapping("/signOut")
  public ModelAndView deleteUser(HttpServletRequest req, @RequestParam("userPw") String userPw) throws Exception {
    ModelAndView mv = new ModelAndView();

    HttpSession session = req.getSession();

    String userId = (String)session.getAttribute("userId");

    if (session.getAttribute("userId") != null) {
      UserEntity userEntity = userService.findUserIdForProfile(userId);
      if (userEntity.getUserPw().equals(userPw)){
        if (userEntity.getDeletedYn() == 'N') {
          userService.deleteUser(userId);
          session.invalidate();
          mv.setViewName("redirect:/home");
        }
        else {
          mv.setViewName("redirect:/login?error=alrdyOutUser");
        }
      }
      else {
        mv.setViewName("redirect:/profile/" + userEntity.getUserId() + "?error=pwChk");
      }
    }
    else {
      mv.setViewName("redirect:/login");
    }
    return mv;
  }

//  //  프로필 이미지 업로드(프사수정)
//  @PutMapping("/uploadProfileImg")
//  public ModelAndView uploadProfileImg(@RequestParam("userIdx") long userIdx, MultipartHttpServletRequest multipart) throws Exception {
//
//    ModelAndView mv = new ModelAndView();
//
//    userService.insertUserProfileImg(userIdx, multipart);
//
//    mv.setViewName("redirect:/profile/{userId}");
//
//    return mv;
//
//  }

//  //  프로필 이미지 삭제
//  @DeleteMapping("/deleteProfileUpload")
//  public String deleteProfileUpload(@RequestParam("userId") String userId) throws Exception{
//
//    userService.deleteProfileImg(userId);
//
//    return "redirect:user/mypage/{userId}";
//
//  }

}
