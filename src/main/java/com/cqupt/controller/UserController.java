package com.cqupt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.cqupt.domin.Commenthistory;
import com.cqupt.domin.Loginfo;
import com.cqupt.domin.Paperuser;
import com.cqupt.domin.queryvo.PaperuserVO;
import com.cqupt.service.CommenthistoryService;
import com.cqupt.service.LoginfoService;
import com.cqupt.service.PaperuserService;
import com.cqupt.service.UserService;
import com.cqupt.utils.*;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 刘博文
 * @since 2022-02-08
 */
//新增功能  注册，历史记录管理，登录信息管理
@RestController
@RequestMapping("/cqupt")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PaperuserService paperuserService;
    @Autowired
    CommenthistoryService commenthistoryService;
    @Autowired
    LoginfoService loginfoService;
//    loadAllcommenthistory  deleteCommentHistory  batchDeleteCommentHistory
@RequestMapping("/loadAllcommenthistory")
public DataGridView loadAllcommenthistory(Commenthistory commenthistory
        , HttpSession session){
    System.out.println(commenthistory);
    Paperuser paperuser=new Paperuser();
    User LoginUser2=(User) session.getAttribute("user");
    System.out.println(LoginUser2);

    IPage<Commenthistory> page = new Page<Commenthistory>(1,10);
    QueryWrapper<Commenthistory> queryWrapper = new QueryWrapper<Commenthistory>();


    queryWrapper.like(commenthistory.getContent()!=null,"content",commenthistory.getContent());


    queryWrapper.orderByDesc("commenttime");
    IPage<Commenthistory> page1 = commenthistoryService.page(page, queryWrapper);
    List<Commenthistory> records = page1.getRecords();


    return new DataGridView(page1.getTotal(),page1.getRecords());
}


    @RequestMapping("deleteCommentHistory")
public ResultObj deleteCommentHistory(Integer id){
    ResultObj result=new ResultObj();
    try {
        commenthistoryService.removeById(id);
        result.setCode(200);
        result.setMsg("删除登录日志成功");
        return result;
    } catch (Exception e) {
        e.printStackTrace();
        result.setCode(400);
        result.setMsg("删除登录日志失败");
        return result;
    }
}
    @RequestMapping("batchDeleteCommentHistory")
    public ResultObj batchDeleteCommentHistory(PaperuserVO paperuser){
        ResultObj result=new ResultObj();
        try {
            Collection<Serializable> idList = new ArrayList<Serializable>();
            //根据多个id进行删除
            for (Integer id : paperuser.getIds()) {
                idList.add(id);
            }
            commenthistoryService.removeByIds(idList);
            result.setCode(200);
            result.setMsg("批量删除成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(400);
            result.setMsg("批量删除失败");
            return result;
        }
    }

@RequestMapping("batchDeleteLoginInfo")
public ResultObj batchDeleteLoginInfo(PaperuserVO paperuser){
    ResultObj result=new ResultObj();
    try {
        Collection<Serializable> idList = new ArrayList<Serializable>();
        //根据多个id进行删除
        for (Integer id : paperuser.getIds()) {
            idList.add(id);
        }
        loginfoService.removeByIds(idList);
        result.setCode(200);
        result.setMsg("批量删除成功");
        return result;
    } catch (Exception e) {
        e.printStackTrace();
        result.setCode(400);
        result.setMsg("批量删除失败");
        return result;
    }
}
//    deleteLoginInfo batchDeleteLoginInfo
//batchDeletePaperuser





    @RequestMapping("deleteLoginInfo")
    public ResultObj deleteLoginInfo(Integer id){
        ResultObj result=new ResultObj();
        try {
            loginfoService.removeById(id);
            result.setCode(200);
            result.setMsg("删除登录日志成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(400);
            result.setMsg("删除登录日志失败");
            return result;
        }
    }
    @RequestMapping("/loadAllloginInfo")
    public DataGridView loadAllloginInfo(Loginfo loginfo
            , HttpSession session){
        System.out.println(loginfo);
        Paperuser paperuser=new Paperuser();
        User LoginUser2=(User) session.getAttribute("user");
        System.out.println(LoginUser2);

        IPage<Loginfo> page = new Page<Loginfo>(1,10);
        QueryWrapper<Loginfo> queryWrapper = new QueryWrapper<Loginfo>();


        queryWrapper.like(loginfo.getLoginname()!=null,"loginname",loginfo.getLoginname());


        queryWrapper.orderByDesc("logintime");
        IPage<Loginfo> page1 = loginfoService.page(page, queryWrapper);
        List<Loginfo> records = page1.getRecords();


        return new DataGridView(page1.getTotal(),page1.getRecords());
    }




    /**
     * 删除阅读记录
     * @paramid
     * @return
     */

//batchDeletePaperuser
    @RequestMapping("batchDeletePaperuser")
    public ResultObj batchDeletePaperuser(PaperuserVO paperuser){
        ResultObj result=new ResultObj();
        try {
            Collection<Serializable> idList = new ArrayList<Serializable>();
            //根据多个id进行删除
            for (Integer id : paperuser.getIds()) {
                idList.add(id);
            }
            paperuserService.removeByIds(idList);
            result.setCode(200);
            result.setMsg("批量删除成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(400);
            result.setMsg("批量删除失败");
            return result;
        }
    }




    @RequestMapping("deletePaperuser")
    public ResultObj deleteInport(Integer id){
        ResultObj result=new ResultObj();
        try {
            paperuserService.removeById(id);
            result.setCode(200);
            result.setMsg("删除阅读记录成功");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(400);
            result.setMsg("删除阅读记录失败");
            return result;
        }
    }


    @RequestMapping("/loadAllpaperuser")
    public DataGridView loadAllpaperuser(Paperuser myPaperuser
            ,HttpSession session){
        System.out.println(myPaperuser);
        Paperuser paperuser=new Paperuser();
        User LoginUser2=(User) session.getAttribute("user");
        System.out.println(LoginUser2);

        IPage<Paperuser> page = new Page<Paperuser>(1,10);
        QueryWrapper<Paperuser> queryWrapper = new QueryWrapper<Paperuser>();

        queryWrapper.eq(LoginUser2.getUsername()!=null,"username",LoginUser2.getUsername());

        queryWrapper.like(myPaperuser.getPapername()!=null,"papername",myPaperuser.getPapername());


        queryWrapper.orderByDesc("readtime");
        IPage<Paperuser> page1 = paperuserService.page(page, queryWrapper);
        List<Paperuser> records = page1.getRecords();


        return new DataGridView(page1.getTotal(),page1.getRecords());
    }

    @RequestMapping("/doSignup")
    public ResultObj dosignup(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String nickname,
                              @RequestParam String email,
                              @RequestParam String address,

                             RedirectAttributes attributes){
        System.out.println("进入了doSignup控制器，现在进行zhuce=======");
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //2.select添加where条件（动态sql）
        queryWrapper.eq("username",username);
        User user=userService.getOne(queryWrapper);
        ResultObj result=new ResultObj();
        if (user == null) {
            //可以注册
            result.setCode(200);
            result.setMsg("注册成功");

            //session里面存放没有MD5加密后的原密码，方便用户前端显示
            User newUser=new User();
            newUser.setAvatar("cquptLOGO.jpg");
            newUser.setCreatetime(new Date());
            newUser.setEmail(email);
            newUser.setNickname(nickname);
            String Md5Pass=MD5Utils.code(password);
            newUser.setPassword(Md5Pass);
            newUser.setType(1);
            newUser.setAvatar("cquptLOGO.jpg");
            newUser.setUpdatetime(new Date());
            newUser.setUsername(username);
            newUser.setAddress(address);
            boolean isSuccess=userService.save(newUser);
            if(isSuccess)
                return result;
        }
        //登录失败
        result.setCode(404);
        result.setMsg("，用户名已经存在");
        attributes.addFlashAttribute("message", "用户注册失败");
        return result;



    }
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
            //2022  4  17 登陆成功记录到日志中
            Loginfo loginfo=new Loginfo();
            loginfo.setLoginip(WebUtils.getRequest().getRemoteAddr());
            loginfo.setLoginname(username);
            loginfo.setLogintime(new Date());
            loginfoService.save(loginfo);
            System.out.println(loginfo);


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
