package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgFileRepository extends JpaRepository<ImgFileEntity, Long> {

  ImgFileEntity findById(long id) throws Exception; // id를 통해 이미지 파일 찾기

}
