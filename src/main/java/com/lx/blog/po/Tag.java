package com.lx.blog.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lux
 * @Date 2020-06-14 17:32
 */
@Entity
@Table(name="t_tag")
@Getter
@Setter
@ToString
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "标签不能为空")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs=new ArrayList<>();

    public Tag() {
    }
}
