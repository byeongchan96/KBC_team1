package bitc.fullstack405.bitcteam3prj.database.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class UserEntity {

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id; // 유저키 pk, board 테이블과 fk
  
  @Column
  private long profileImg; // 프로필사진, img_file 테이블의 id 와 fk
  
  @Column(nullable = false)
  private String userId;
  
  @Column(nullable = false)
  private String userPw;
  
  @Column(nullable = false)
  private String email;
  
  @Column(nullable = false)
  private LocalDateTime joinedAt = LocalDateTime.now(); // 유저 가입일(생성일)

  @Column(nullable = false)
  private char gender; // 성별
  
  @Column(nullable = false)
  private int age; // 나이
  
  @Column
  private String movieCate; // 선호 영화장르
}
