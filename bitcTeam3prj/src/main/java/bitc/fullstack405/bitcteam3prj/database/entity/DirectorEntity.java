package bitc.fullstack405.bitcteam3prj.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.annotation.Documented;
import java.util.List;

@Data
@Entity
@Table(name = "director")
@NoArgsConstructor
@AllArgsConstructor
public class DirectorEntity {

  @Id
  @Column(nullable = false)
  private long id; // 감독 id, pk, movie 테이블에 fk

  @Column(nullable = false)
  private String name;

  @ToString.Exclude
  @OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
  private List<MovieEntity> movieList;
}
