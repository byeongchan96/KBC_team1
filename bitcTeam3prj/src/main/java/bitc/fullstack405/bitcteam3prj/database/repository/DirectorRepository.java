package bitc.fullstack405.bitcteam3prj.database.repository;

import bitc.fullstack405.bitcteam3prj.database.entity.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DirectorRepository extends JpaRepository<DirectorEntity, Long> {
    Optional<DirectorEntity> findByName(String name);
}
