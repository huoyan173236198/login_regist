package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.support.Parameter;
import com.qf.entity.Email;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import com.qf.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * @version 1.0
 * @user 焰
 * @date 2019/6/29 14:52
 */
@Controller
@RequestMapping("regist")
public class RegistController {
    @Reference
    private IEmailService emailService;
    @Reference
    private IUserService userService;
    @ResponseBody
    @RequestMapping("sendCode")
    public String sendCode(@RequestBody Email email, HttpServletRequest request){
        System.out.println(email.getEmail_name());
        Integer eChange=1;
        Integer code= (Integer) emailService.senEmail(email.getEmail_name(),eChange);
        request.getSession().setAttribute("code",code);
        return "验证码已发送";
    }


    @RequestMapping("inRegist")
    public String inRegist(){

        return "regist";
    }

    @RequestMapping("registUser")
    public String registUser(@RequestBody User user,@RequestBody Email email,HttpServletRequest request){
        Integer code=(Integer)request.getSession().getAttribute("code");
        if(code==email.getCode()){
            userService.inster(user);
        }
        return "index";
    }
}
