package com.cqupt.service.impl;

import com.cqupt.utils.AppFileUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

public class FileDownload {
    public boolean Download(String name, HttpServletResponse response) throws Exception {
        System.out.println(name);
        String filePath= AppFileUtils.UPLOAD_PATH+"/"+name;
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return false;
        }
        System.out.println("filepath==========="+filePath);
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(name, "UTF-8"));

        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
        System.out.println("文件下载成功");
        return true;
    }

}
