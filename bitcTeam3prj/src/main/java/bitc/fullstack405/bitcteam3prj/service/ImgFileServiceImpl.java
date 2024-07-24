package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;
import bitc.fullstack405.bitcteam3prj.database.repository.ImgFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgFileServiceImpl implements ImgFileService {
    @Autowired
    private ImgFileRepository imgFileRepository;

    @Override
    public void saveAllImageFile(List<ImgFileEntity> imgFileEntityList) throws Exception {
        imgFileRepository.saveAll((imgFileEntityList));
    }
}
