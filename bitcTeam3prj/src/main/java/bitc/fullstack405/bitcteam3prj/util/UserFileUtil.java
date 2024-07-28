package bitc.fullstack405.bitcteam3prj.util;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserFileUtil {

  public List<ImgFileEntity> parseUserFileInfo(long id, MultipartHttpServletRequest multipart) throws Exception{

    if (ObjectUtils.isEmpty(multipart)) {
      return null;
    }

    List<ImgFileEntity> fileList = new ArrayList<>();

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    ZonedDateTime current = ZonedDateTime.now();

    String path = "C:/fullstack405/images/" + current.format(format);

    File file = new File(path);

    if (file.exists() == false) {
      file.mkdirs();
    }

    Iterator<String> iterator = multipart.getFileNames();

    String newFileName;
    String originalFileExt;
    String contentType;

    while (iterator.hasNext()) {
      String name = iterator.next();
      List<MultipartFile> multipartFileList = multipart.getFiles(name);

      for (MultipartFile uploadFile : multipartFileList) {
        contentType = uploadFile.getContentType();

        if (ObjectUtils.isEmpty(contentType)) {
          break;
        }
        else {
          if (contentType.contains("image/jpeg")) {
            originalFileExt = ".jpg";
          }
          else if (contentType.contains("image/png")) {
            originalFileExt = ".png";
          }
          else if (contentType.contains("immge/gif")) {
            originalFileExt = ".gif";
          }
          else {
            break;
          }
        }

        newFileName = System.nanoTime() + originalFileExt;

        UserEntity userEntity = new UserEntity();
        ImgFileEntity imgFileEntity = new ImgFileEntity();

        imgFileEntity.setId(id);
        imgFileEntity.setOriName(uploadFile.getOriginalFilename());
        imgFileEntity.setSaved_path(path + "/" + newFileName);
        imgFileEntity.setSavedName(userEntity.getUserId() + imgFileEntity.getOriName());
        imgFileEntity.setUser(userEntity);

        fileList.add(imgFileEntity);

        file = new File(path + "/" + newFileName);
        uploadFile.transferTo(file);
      }
    }

    return fileList;
  }
}
