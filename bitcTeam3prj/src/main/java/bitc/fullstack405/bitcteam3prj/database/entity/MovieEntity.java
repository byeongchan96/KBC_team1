package bitc.fullstack405.bitcteam3prj.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MovieEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private long id; // 영화 id(기본키), api 사용시 AI 필요?

  @ManyToOne
  @JoinColumn(name = "director_id")
  @ToString.Exclude
  private DirectorEntity director; // 감독id, director 테이블과 외래키

  @ToString.Exclude
  @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
  private ImgFileEntity posterImg; // 영화포스터, movie_article 테이블과 외래키

  @Column(nullable = false)
  private String movieName; // 영화이름

  private LocalDateTime openDt; // 개봉일

  private int showTm; // 러닝타임

  private String movieCate; // 영화장르

  @Column(length = 1000)
  private String movieDisc; // 영화설명

  private String company; // 제작사

  private String grade; // 상영 등급
}
