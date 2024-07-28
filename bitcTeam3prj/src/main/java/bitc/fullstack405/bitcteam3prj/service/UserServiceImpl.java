package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import bitc.fullstack405.bitcteam3prj.database.repository.UserImgFileRepository;
import bitc.fullstack405.bitcteam3prj.database.repository.UserRepository;
import bitc.fullstack405.bitcteam3prj.util.UserFileUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserFileUtil userFileUtil;
  @Autowired
  private UserImgFileRepository userImgFileRepository;

  //  로그인 시도 유저 존재 확인
  @Override
  public int isUserInfo(String userId, String userPw) throws Exception {
    int result = userRepository.countByUserIdAndUserPw(userId, userPw);

    return result;
  }

//  Id 중복 여부 확인
  @Override
  public int userIdCheck(String userId) throws Exception {
    int result = userRepository.countByUserId(userId);

    return result;
  }

//  이메일 중복 여부 확인
  @Override
  public int userEmailCheck(String email) throws Exception {
    int result = userRepository.countByEmail(email);

    return result;
  }

//  회원가입 정보 DB 입력
  @Override
  public void insertUser(UserEntity userEntity) throws Exception {
    userRepository.save(userEntity);
  }

//  userId, email로 유저 존재여부 확인
  @Override
  public UserEntity findPassword(String userId, String email) throws Exception {
    UserEntity entity = userRepository.findByUserIdAndEmail(userId, email);
    return entity;
  }

// userPw 변경
  @Override
  public void updateUserPw(String userId, String userPw) throws Exception {

    userRepository.changePw(userPw, userId);

  }

//  userId 찾기
  @Override
  public UserEntity findUserId(String email, String userPw) throws Exception {

    return userRepository.findByEmailAndUserPw(email, userPw);

  }

  @Override
  public UserEntity findUserIdForProfile(String userId) throws Exception {

    return userRepository.findByUserId(userId);
  }

  @Override
  public ImgFileEntity findImgIdForProfile(long id) throws Exception {
    return userImgFileRepository.findById(id);
  }

  @Override
  public void deleteUser(String userId) throws Exception {
    userRepository.signOut(userId);
  }

  @Override
  public UserEntity findByUserIdCheckSignOut(String userId) throws Exception {
    return userRepository.findByUserId(userId);
  }

  @Override
  public void insertUserProfileImg(long userIdx, MultipartHttpServletRequest multipart) throws Exception {

    userFileUtil.parseUserFileInfo(userIdx, multipart);

  }

  @Override
  public void deleteProfileImg(String userId) throws Exception {
    userRepository.deletingUserProfileImg(userId);
  }
}
