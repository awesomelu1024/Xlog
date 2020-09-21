package com.lx.blog.service.impl;

import com.lx.blog.NotFoundError;
import com.lx.blog.dao.TypeRepository;
import com.lx.blog.po.Type;
import com.lx.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Lux
 * @Date 2020-06-16 11:27
 */
@Transactional
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.findOne(id);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public Type updateType(Long id, Type type) {
        Type t=typeRepository.findOne(id);
        if(t==null){
            throw new NotFoundError("不存在该分类");
        }

        BeanUtils.copyProperties(type,t);
        typeRepository.save(t);

        return null;
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.delete(id);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort=new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable=new PageRequest(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }
}
