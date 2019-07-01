package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import com.qf.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @version 1.0
 * @user 焰
 * @date 2019/6/29 14:52
 */
@Controller
@RequestMapping("repassword")
public class RePasswordController {
    @Reference
    private IUserService userService;
    @Reference
    private IEmailService emailService;
    @ResponseBody
    @RequestMapping("getusername")
    public String toLogin(@RequestBody User user, HttpServletRequest request){
        System.out.println(user);
        String email_name= userService.loginByUsername(user);
        emailService.senEmail(email_name,2);
        return "邮件已发送";
    }

    @RequestMapping("tochange")
    public String tochange(){
        return "tochangepw";
    }

    @RequestMapping("updatepw")
    public String updatepw(@RequestBody User user){

        userService.updateByUsername(user);

        return "index";
    }
}
