package com.cqupt.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqupt.domin.Paper;
import com.cqupt.mapper.UserMapper;
import com.cqupt.service.UserService;
import com.cqupt.utils.MD5Utils;
import com.cqupt.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cqupt.domin.User;
import javax.servlet.http.HttpSession;

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
            //user.setPassword(null);
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


}
