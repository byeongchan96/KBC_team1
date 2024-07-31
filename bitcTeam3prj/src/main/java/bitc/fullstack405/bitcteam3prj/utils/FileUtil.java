package bitc.fullstack405.bitcteam3prj.utils;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtil {

  public ImgFileEntity uploadFile(HttpServletRequest req, HttpServletResponse resp, String userId) throws ServletException, IOException {

    Part part = req.getPart("uploadFile");
    String partHeader = part.getHeader("content-disposition");
    String[] phArr = partHeader.split("filename=");
    String oriFileName = phArr[1].trim().replace(".jpg", "").replace("\"", "");


    File rootPath = new File("C:fullstack405/spring/Team3Temp/bitcTeam3prj/src/main/resources/static/image");
    String savePath = rootPath.getAbsolutePath() +  "/"  + oriFileName + ".jpg";

    String savedFileName = oriFileName + ".jpg";

    File f = new File(savePath);

    if (!oriFileName.isEmpty()) {
      part.write(savedFileName);


      ImgFileEntity imgFileEntity = new ImgFileEntity();
      imgFileEntity.setOriName(oriFileName);
      imgFileEntity.setSavedName(userId);
      imgFileEntity.setSavedPath(savePath);
      return imgFileEntity;
    }
    else {
      return null;
    }
  }

  public static void deleteFile(String fileName) {

    File rootPath = new File("src/main/resources/static/image");
    String savePath = rootPath.getAbsolutePath() +  "/"  + fileName;

    File file = new File(savePath + File.separator + fileName + ".jpg");

    if (file.exists()) {
      file.delete();
    }
  }
}

