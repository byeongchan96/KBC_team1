package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.BoardCommentEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardCommentRepository extends JpaRepository<BoardCommentEntity, Long> {
    List<BoardCommentEntity> findAllByUser(UserEntity user);
}
