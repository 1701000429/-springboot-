package com.cqupt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.domin.Paper;
import com.cqupt.mapper.UserMapper;
import com.cqupt.service.UserService;
import com.cqupt.utils.AppFileUtils;
import com.cqupt.utils.MD5Utils;
import com.cqupt.utils.ResultObj;
import com.cqupt.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cqupt.domin.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
@RestController
@RequestMapping("/cqupt")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/doLogin")
    public ResultObj doLogin(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam Integer type,
                             HttpSession session,
                             RedirectAttributes attributes){
        System.out.println("进入了doLogin控制器，现在进行登录=======");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //2.select添加where条件（动态sql）
        queryWrapper.eq("username",username);
        queryWrapper.eq("password", MD5Utils.code(password));
        queryWrapper.eq("type",type);
        queryWrapper.last("  LIMIT 1");
        //queryWrapper.ge("createtime",paper.getCreatetime());
        User user=userService.getOne(queryWrapper);
        //User user = userService.doLogin(username, password,type);

        ResultObj result=new ResultObj();
        if(type==1){
            result.setData("1");
        }
        else
            if(type==2)
        {
            result.setData("2");
        }
        if (user != null) {
            //登陆成功
            result.setCode(200);
            result.setMsg("登录成功");

            //session里面存放没有MD5加密后的原密码，方便用户前端显示
            user.setPassword(password);
            session.setAttribute("user",user);
            return result;
        } else {
            //登录失败
            result.setCode(404);
            result.setMsg("登录失败");
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return result;
        }

    }


    /**
     * 修改用户
     * @param userVo
     * @return
     */
    @RequestMapping("/user/updateUserInfo")
    public ResultObj updateUserInfo(User userVo,HttpSession session){
        String password=userVo.getPassword();
        userVo.setPassword(MD5Utils.code(password));

        userVo.setUpdatetime(new Date());

        ResultObj result=new ResultObj();
        result.setCode(200);
        result.setMsg("修改成功");
        try {
            //更新用户信息
            userService.updateById(userVo);
            //更新session
            userVo.setPassword(password);



            session.setAttribute("user",userVo);
            System.out.println("更新session==");
            System.out.println(userVo);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(400);
            result.setMsg("修改失败");
            return result;
        }
    }


    //得到用户头像的名称   不是绝对路径！！
    @RequestMapping("/user/getUserAvatar")
    public ResultObj getUserAvatar( Integer id){
        User user =userService.getById(id);
        //头像名
        String Avatar=user.getAvatar();
        //拼接绝对路径 默认为D:/CQUPTupload
        String filePath= AppFileUtils.UPLOAD_PATH;
        ResultObj result=new ResultObj();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(Avatar);
        System.out.println("前端请求头像地址path====");
        System.out.println(result);
        return result;
    }

    /**
     * 图片下载
     */
    @RequestMapping("/file/showImageByPath")
    public ResponseEntity<Object> showImageByPath(String path){
        //jue dui lu jing
        System.out.println("前端请求头像img====");
        return AppFileUtils.createResponseEntity(path);
    }


    /**
     * 图片下载
     */
    @RequestMapping("/file/showImageByName")
    public ResponseEntity<Object> showImageByName(String path){
        //拼接绝对路径 默认为D:/CQUPTupload
        String filePath= AppFileUtils.UPLOAD_PATH;
        //jue dui lu jing
        System.out.println("前端请求头像img====");
        return AppFileUtils.createResponseEntity(filePath+"/"+path);
    }



    //头像上传成功后  写进数据库
    @PostMapping("/admin/Avatarupload")
    public ResultObj Avatarupload(@RequestParam MultipartFile file,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  HttpSession session) {
        ResultObj res=new ResultObj(400,"上传失败");

        if (file.isEmpty()) {
            return res;
        }
        //写进数据库
        User userTemp=(User)session.getAttribute("user");
        User user=userService.getById(userTemp.getId());
        String avatarFileName=file.getOriginalFilename();
        user.setAvatar(avatarFileName);
        userService.updateById(user);
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
}
