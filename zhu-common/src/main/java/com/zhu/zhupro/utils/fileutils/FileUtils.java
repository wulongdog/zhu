package com.zhu.zhupro.utils.fileutils;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {

    public static String path = "upload/";


    public static String setPhotoPath(MultipartFile file,String basePath) throws IOException {
        String originalFilename = file.getOriginalFilename();
        //后缀名
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String photoPath =UUID.randomUUID().toString()+substring;

        File directory = new File(basePath);
        String finalPath = basePath+photoPath;
        if (!directory.exists()) {
            directory.mkdirs();
        }
        file.transferTo(new File(finalPath));
        return "upload/"+photoPath;
    }


}
