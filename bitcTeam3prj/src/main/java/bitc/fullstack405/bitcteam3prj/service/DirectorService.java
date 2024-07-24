package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.DirectorEntity;

import java.util.List;

public interface DirectorService {
    void saveAllDirector(List<DirectorEntity> director) throws Exception;

    DirectorEntity getDirectorByName(String name) throws Exception;
}
