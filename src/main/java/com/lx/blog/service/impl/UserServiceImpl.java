package com.lx.blog.service.impl;

import com.lx.blog.dao.UserRepository;
import com.lx.blog.po.User;
import com.lx.blog.service.UserService;
import com.lx.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Lux
 * @Date 2020-06-15 20:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username,MD5Utils.code(password));
        return user;
    }
}
