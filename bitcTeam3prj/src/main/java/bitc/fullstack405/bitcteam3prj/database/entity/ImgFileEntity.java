package bitc.fullstack405.bitcteam3prj.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name = "img_file")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ImgFileEntity {

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id; // 이미지 파일 id, pk

  @Column(nullable = false)
  private String oriName; // 원본파일명

  @Column(nullable = false)
  private String savedName; // 저장파일명

  @Column(nullable = false)
  private String savedPath; // 저장경로


  @OneToOne
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  private UserEntity user;

  @OneToOne
  @JoinColumn(name="movie_id")
  @ToString.Exclude
  private MovieEntity movie;
}