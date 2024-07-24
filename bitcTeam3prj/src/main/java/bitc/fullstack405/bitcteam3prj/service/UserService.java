package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;

import java.util.List;

public interface UserService {

  int isUserInfo(String userId, String userPw) throws Exception;

  int userIdCheck(String userId) throws Exception; // 회원가입 유저 ID 체크

  int userEmailCheck(String email) throws Exception; // 회원가입 유저 email 체크

  void insertUser(UserEntity userEntity) throws Exception;  // 회원가입 정보 DB 입력
}
