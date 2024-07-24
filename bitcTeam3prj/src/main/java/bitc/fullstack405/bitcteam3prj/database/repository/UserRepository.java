package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  int countByUserIdAndUserPw(String userId, String userPw); // 로그인 회원정보 확인

  int countByUserId(String userId); // 회원가입 UserId 존재여부 확인

  int countByEmail(String email); // 회원가입 Email 존재여부 확인

}
