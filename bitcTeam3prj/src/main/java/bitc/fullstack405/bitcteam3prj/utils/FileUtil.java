package bitc.fullstack405.bitcteam3prj.utils;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;
import bitc.fullstack405.bitcteam3prj.database.entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtil {

  public void uploadFile(HttpServletRequest req) throws ServletException, IOException {

    Part part = req.getPart("uploadFile");
    String partHeader = part.getHeader("content-disposition");
    String[] phArr = partHeader.split("filename=");
    String oriFileName = phArr[1].trim().replace(".jpg", "").replace("\"", "");


    File rootPath = new File("src/main/resources/static/image");
    String savePath = rootPath.getAbsolutePath() +  "/"  + oriFileName + ".jpg";

    File f = new File(savePath);
    if(f.exists()){
      System.out.println("Exists File : " + oriFileName);
      return;
    }

    if (!oriFileName.isEmpty()) {
      part.write(savePath);
    }
  }

  public static void deleteFile(String fileName, String saveDir) {

    File file = new File(saveDir + File.separator + fileName + ".jpg");

    if (file.exists()) {
      file.delete();
    }
  }
}

