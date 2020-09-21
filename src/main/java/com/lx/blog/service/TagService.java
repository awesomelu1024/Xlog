package com.lx.blog.service;

import com.lx.blog.po.Tag;
import com.lx.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: Lux
 * @Date 2020-06-16 21:34
 */
public interface TagService {

    //新增标签
    Tag saveTag(Tag tag);

    //id查找标签
    Tag getTag(Long id);

    //name查找标签
    Tag getTagByName(String name);

    //分页查询
    Page<Tag> listTag(Pageable pageable);

    //修改标签
    Tag updateTag(Long id,Tag tag);

    //删除标签
    void deletTag(Long id);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    //展示页面 中的标签列表
    List<Tag> listTagTop(Integer size);//选择展示几个

}
