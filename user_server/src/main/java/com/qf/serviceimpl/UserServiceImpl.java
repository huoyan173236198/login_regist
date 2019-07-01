package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.UserMapper;
import com.qf.entity.User;
import com.qf.service.IEmailService;
import com.qf.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @user 焰
 * @date 2019/6/29 14:19
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Reference
    private IEmailService emailService;
    @Override
    public boolean inster(User user) {
        int count=userMapper.insert(user);
        return true;
    }

    @Override
    public boolean loginByInfo(User user) {
        Map<String,Object> map=new HashMap<>();
            map.put("username",user.getUsername());
            map.put("password",user.getPassword());
            List<User> users=userMapper.selectByMap(map);
            if(users.size()>0){
                return true;
            }
        return false;
    }

    @Override
    public String loginByUsername(User user) {
        Map<String,Object> map=new HashMap<>();
        map.put("username",user.getUsername());
        List<User> users=userMapper.selectByMap(map);
        String email=null;
        for (User u:users){
            email=u.getEmail_name();
        }
        return email;
    }

    @Override
    public Integer updateByUsername(User user) {
        String username=user.getUsername();
        /*userMapper.update(@Param("User") String username,null);*/
        return null;
    }
}
