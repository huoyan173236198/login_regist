package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 1.0
 * @user 焰
 * @date 2019/6/29 14:52
 */
@Controller
@RequestMapping("login")
public class LoginController {
    @Reference
    private IUserService userService;
    @RequestMapping("toLogin")
    public String toLogin(User user){
        System.out.println(user);
        Integer uChange=1;
        boolean flag=userService.loginByInfo(user);
        if(flag){
            return "welcome";
        }
        return "index";
    }
    @RequestMapping("torepassword")
    public String torepassword(){
        return "repassword";
    }
}
