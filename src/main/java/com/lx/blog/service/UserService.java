package com.lx.blog.service;

import com.lx.blog.po.User;

/**
 * @Author: Lux
 * @Date 2020-06-15 20:28
 */
public interface UserService {

    public User checkUser(String username, String password);
}
