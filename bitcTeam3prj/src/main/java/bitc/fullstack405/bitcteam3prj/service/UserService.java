package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;

import java.util.List;

public interface UserService {

  int isUserInfo(String userId, String userPw) throws Exception;
}
