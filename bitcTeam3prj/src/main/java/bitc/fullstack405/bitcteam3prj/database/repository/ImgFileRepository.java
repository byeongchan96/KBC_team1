package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgFileRepository extends JpaRepository<ImgFileEntity, Long> {

  ImgFileEntity findBySavedName(String savedName) throws Exception; // 저장된 이미지 파일 이름을 통해 이미지 찾기기

}
