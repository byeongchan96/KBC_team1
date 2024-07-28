package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImgFileRepository extends JpaRepository<ImgFileEntity, Integer> {

  ImgFileEntity findById(long id) throws Exception;
}
