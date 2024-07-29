package bitc.fullstack405.bitcteam3prj.service;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;
import bitc.fullstack405.bitcteam3prj.database.repository.ImgFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService{

  @Autowired
  ImgFileRepository imgFileRepository;


  @Override
  public ImgFileEntity findById(long id) throws Exception {
    return imgFileRepository.findById(id);
  }
}
