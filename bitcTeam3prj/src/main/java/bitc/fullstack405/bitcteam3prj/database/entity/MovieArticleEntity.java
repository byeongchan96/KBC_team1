package bitc.fullstack405.bitcteam3prj.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "movie_article")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MovieArticleEntity extends BaseEntity{

  @Id
  @Column(nullable = false)
  private long id; // 영화정보 id, pk, manager 테이블과 fk

  @Column(nullable = false)
  private long movieId; // 영화 id, movie 테이블과 fk

  @ManyToOne
  @JoinColumn(name="manager_id")
  @ToString.Exclude
  @Column(nullable = false)
  private ManagerEntity manager; // 관리자 id, manager 테이블과 fk

  @Column
  private int viewCnt; // 조회수

}
