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
 * @Date 2020-06-15 17:19
 */
@Entity
@Table(name = "t_type")
@Getter
@Setter
@ToString
public class Type {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "分类名不能为空")
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs=new ArrayList<>();

    public Type() {
    }
}
