package com.lx.blog.service.impl;

import com.lx.blog.NotFoundError;
import com.lx.blog.dao.TagRepository;
import com.lx.blog.po.Tag;
import com.lx.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lux
 * @Date 2020-06-16 21:34
 */
@Transactional
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.findOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag updateTag(Long id,Tag tag) {
        Tag t=tagRepository.findOne(id);
        if(t==null){
            throw new NotFoundError("不存在该标签");
        }
        BeanUtils.copyProperties(tag,t);
        tagRepository.save(t);

        return null;

    }

    @Override
    public void deletTag(Long id) {
        tagRepository.delete(id);
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAll(convertToList(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort=new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable=new PageRequest(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    private  List<Long> convertToList(String ids){
        List<Long> list =new ArrayList<>();
        if(!"".equals(ids)&&ids!=null){
            String[] idarray=ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }

        return list;
    }
}
