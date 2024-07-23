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
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieEntity {

  @Id
  @Column(nullable = false)
  private long id; // 영화 id(기본키), api 사용시 AI 필요?

  @Column
  private long directorId; // 감독id, director 테이블과 외래키

  @Column
  private long posterImg; // 영화포스터, movie_article 테이블과 외래키

  @Column(nullable = false)
  private String movieName; // 영화이름

  @Column
  private LocalDateTime openDt; // 개봉일

  @Column
  private int showTm; // 러닝타임

  @Column
  private String movieCate; // 영화장르

  @Column
  private String movieDisc; // 영화설명

}
