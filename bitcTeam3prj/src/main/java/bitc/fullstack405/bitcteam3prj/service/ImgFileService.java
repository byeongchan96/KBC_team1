package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;

import java.util.List;

public interface ImgFileService {
    void saveAllImageFile(List<ImgFileEntity> imgFileEntityList) throws Exception;
}
