package com.fernando.mptest.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fernando.mptest.model.User;
import com.fernando.mptest.service.IUserService;
import com.fernando.mptest.utils.Consts;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fernando
 * @since 2021-10-20
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping (value = "/findAllUser", method = RequestMethod.GET)
    public List<User> findAllUser(){
        return userService.findAllUser();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean flag = userService.verifyPassword(username, password);
        if(flag){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"登陆成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"用户名或密码错误");
        return jsonObject;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Object register(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String phoneNum = request.getParameter("phoneNum").trim();
        String email = request.getParameter("email").trim();
        String avatar = request.getParameter("avatar").trim();

        if(username.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名不能为空");
            return jsonObject;
        }

        User userCheck = userService.getUserByName(username);
        if(userCheck != null){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"用户名已存在");
            return jsonObject;
        }

        if(password.equals("")){
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG,"密码不能为空");
            return jsonObject;
        }

        //保存到前端用户的对象中
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNum(phoneNum);
        user.setEmail(email);
        user.setAvatar(avatar);
        boolean flag = userService.insert(user);
        if(flag){   //保存成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    @RequestMapping (value = "/UserInfo", method = RequestMethod.GET)
    public User getUserByName(@Param("username")String username){
        return userService.getUserByName(username);
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Object update(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String phoneNum = request.getParameter("phoneNum").trim();
        String email = request.getParameter("email").trim();
        String avatar = request.getParameter("avatar").trim();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNum(phoneNum);
        user.setEmail(email);
        boolean flag = userService.update(user);

        if(flag){   //修改成功
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }
}
