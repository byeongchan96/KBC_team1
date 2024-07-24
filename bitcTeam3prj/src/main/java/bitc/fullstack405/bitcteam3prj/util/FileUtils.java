package bitc.fullstack405.bitcteam3prj.util;

import bitc.fullstack405.bitcteam3prj.database.entity.ImgFileEntity;

import java.io.*;
import java.net.URL;

public class FileUtils {
    public static void UrlToImage(String savePath, String imageURL, String fileName) {
        fileName = fileName.replace("?", "");
        fileName = fileName.replace(":", "-");
        savePath = savePath + fileName + ".jpg";

        File f = new File(savePath);
        if(f.exists()){
            System.out.println("Exists File : " + fileName);
            return;
        }


        URL url = null;
        try {
            url = new URL(imageURL);

            InputStream inputStream = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(savePath);
            byte buffer[] = new byte[1024];
            int byteRead;
            while ((byteRead = inputStream.read(buffer, 0, 1024)) != -1) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Create File : " + fileName);
    }
}
