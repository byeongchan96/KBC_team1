package bitc.fullstack405.bitcteam3prj.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id; // 유저키 pk, board 테이블과 fk


  @OneToOne
  @JoinColumn(name="img_id")
  private ImgFileEntity profileImg; // 프로필사진, img_file 테이블의 id 와 fk
  
  @Column(nullable = false)
  private String userPw;
  
  @Column(nullable = false)
  private String email;

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime joinedAt;// 유저 가입일(생성일)

  @Column(nullable = false)
  private char gender; // 성별
  
  @Column(nullable = false)
  private int age; // 나이
  
  private String movieCate; // 선호 영화장르

  @ToString.Exclude
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  List<BoardEntity> boardList;

  @ToString.Exclude
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  List<BoardLikeEntity> boardLikeList;

  @ToString.Exclude
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  List<MovieBoardRatingEntity> movieRatingList;
}
