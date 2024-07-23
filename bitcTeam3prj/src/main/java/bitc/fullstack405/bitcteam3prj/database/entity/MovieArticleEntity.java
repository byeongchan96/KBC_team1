package bitc.fullstack405.bitcteam3prj.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "movie_article")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieArticleEntity {

  @Id
  @Column(nullable = false)
  private long id; // 영화정보 id, pk, manager 테이블과 fk

  @Column(nullable = false)
  private long movieId; // 영화 id, movie 테이블과 fk

  @Column(nullable = false)
  private long managerId; // 관리자 id, manager 테이블과 fk

  @Column
  private int viewCnt; // 조회수

  @Column(nullable = false)
  private LocalDateTime createAt = LocalDateTime.now(); // 영화정보 작성일

  @Column(nullable = false)
  private String createBy; // 영화정보 작성자

  @Column
  private LocalDateTime updateAt; // 영화정보 수정일

  @Column
  private String updateBy; // 영화정보 수정자

}
