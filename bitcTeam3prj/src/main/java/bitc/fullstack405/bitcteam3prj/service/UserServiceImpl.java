package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import bitc.fullstack405.bitcteam3prj.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;

  @Override
  public int isUserInfo(String userId, String userPw) throws Exception {
    int result = userRepository.countByUserIdAndUserPw(userId, userPw);

    return result;
  }

  @Override
  public int userIdCheck(String userId) throws Exception {
    int result = userRepository.countByUserId(userId);

    return result;
  }

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
}
