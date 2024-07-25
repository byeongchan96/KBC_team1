package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;

public interface UserService {

  int isUserInfo(String userId, String userPw) throws Exception;

  int userIdCheck(String userId) throws Exception; // 회원가입 유저 ID 체크

  int userEmailCheck(String email) throws Exception; // 회원가입 유저 email 체크

  void insertUser(UserEntity userEntity) throws Exception;  // 회원가입 정보 DB 입력

  UserEntity findPassword(String userId, String email) throws Exception; // userPw찾기

  void updateUserPw(String userId, String userPw) throws Exception; // 암호찾기를 통한 암호변경

  UserEntity findUserId(String email, String userPw) throws Exception; // userId 찾기
}
