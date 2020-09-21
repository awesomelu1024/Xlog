package com.lx.blog.service;

import com.lx.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: Lux
 * @Date 2020-06-16 11:23
 */
public interface TypeService {

    //新增分类
    Type saveType(Type type);

    //id查询分类
    Type getType(Long id);

    //name查询分类
    Type getTypeByName(String name);

    //分页查询
    Page<Type> listType(Pageable pageable);

    //修改分类
    Type updateType(Long id,Type type);

    //删除分类
    void deleteType(Long id);

    List<Type> listType();

    //展示页面中的分类列表
    List<Type> listTypeTop(Integer size);
}
