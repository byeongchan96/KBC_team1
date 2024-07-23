package bitc.fullstack405.bitcteam3prj.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "director")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DirectorEntity {

  @Id
  @Column(nullable = false)
  private long id; // 감독 id, pk, movie 테이블에 fk
}
