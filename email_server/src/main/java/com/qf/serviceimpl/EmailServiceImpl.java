package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * @version 1.0
 * @user 焰
 * @date 2019/6/29 14:28
 */
@Service
public class EmailServiceImpl implements IEmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public Object senEmail(String email,Integer eChange) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            messageHelper.setFrom("huoyan173236198@sina.com");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        if (eChange==1){
            int code = (int)(Math.random()*(9999-1000+1))+1000;

            try {
                messageHelper.addTo(email, "验证码为："+code);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            javaMailSender.send(mimeMessage);

            return code;
        }else if (eChange==2){
            try {
                messageHelper.setText("请点击<a href='http://localhost:8080/repassword/tochangepw'>这里</a>找回密码~", true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            javaMailSender.send(mimeMessage);
        }


       return null;
    }
}
