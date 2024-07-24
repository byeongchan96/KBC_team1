package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.DirectorEntity;
import bitc.fullstack405.bitcteam3prj.database.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public void saveAllDirector(List<DirectorEntity> director) throws Exception {
        directorRepository.saveAll(director);
    }

    @Override
    public DirectorEntity getDirectorByName(String name) throws Exception {

        var opt = directorRepository.findByName(name);

        return (DirectorEntity) opt.orElse(null);
    }
}
