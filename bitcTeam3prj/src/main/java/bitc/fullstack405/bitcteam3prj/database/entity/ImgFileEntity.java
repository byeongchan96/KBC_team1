package bitc.fullstack405.bitcteam3prj.database.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "img_file")
@AllArgsConstructor
@NoArgsConstructor
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
  private String saved_path; // 저장경로


  @OneToOne(mappedBy = "profileImg", cascade = CascadeType.ALL)
  @ToString.Exclude
  private UserEntity user;

  @OneToOne(mappedBy = "posterImg", cascade = CascadeType.ALL)
  @ToString.Exclude
  private MovieEntity movie;
}