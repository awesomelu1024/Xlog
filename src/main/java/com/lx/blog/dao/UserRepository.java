package com.lx.blog.dao;

import com.lx.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Lux
 * @Date 2020-06-15 20:30
 */
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUsernameAndPassword(String username,String password);
}
