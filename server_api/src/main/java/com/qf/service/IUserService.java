package com.qf.service;

import com.qf.entity.User;

/**
 * @version 1.0
 * @user 焰
 * @date 2019/6/29 11:59
 */
public interface IUserService {
    boolean inster(User user);

    boolean loginByInfo (User user);

    String loginByUsername (User user);

    Integer updateByUsername (User user);
}
