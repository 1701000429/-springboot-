package com.cqupt.controller;

import com.cqupt.domin.User;
import com.cqupt.service.impl.FileDownload;
import com.cqupt.utils.AppFileUtils;
import com.cqupt.utils.ResultObj;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/cqupt")
public class FileController {


    @PostMapping("/admin/fileupload")
    public ResultObj fileupload(@RequestParam MultipartFile file,
                             HttpServletRequest request,
                             HttpServletResponse response) {
        ResultObj res=new ResultObj(400,"上传失败");

        if (file.isEmpty()) {
            return res;
        }

        String fileName = file.getOriginalFilename();
        String filePath=AppFileUtils.UPLOAD_PATH;

        File dest = new File(filePath + "/"+fileName);
        try {
            file.transferTo(dest);
            res.setCode(200);
            res.setData(fileName);
            res.setMsg("上传成功");
            return res;
        } catch (IOException e) {
            System.out.println(e);
        }
        return res;
    }

    @RequestMapping("/user/paperZipDownload/{fileName}")
    public void logDownload(@PathVariable String fileName, HttpServletResponse response) throws Exception {

        FileDownload fileDownload=new FileDownload();
        fileDownload.Download(fileName, response);
    }





}
