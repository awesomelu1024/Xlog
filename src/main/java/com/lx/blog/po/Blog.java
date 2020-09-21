package com.lx.blog.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Lux
 * @Date 2020-06-14 17:21
 */
@Entity
@Table(name = "t_blog")
@Getter
@Setter
@ToString
public class Blog {
    @Id
    @GeneratedValue
    private Long id;

    private String title;//标题

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;//内容
    private String firstPicture;//首图
    private String flag;//标记
    private Integer views;//浏览次数
    private boolean appreciation;//赞赏开启
    private boolean shareStatement;//版权开启
    private boolean commentable;//评论开启
    private boolean published;//发布
    private boolean recommend;//推荐
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;//创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;//更新时间
    private String description;

    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags=new ArrayList<>();

    @Transient
    private String tagIds;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments=new ArrayList<>();

    public Blog() {
    }

    public void init() {
        this.tagIds=tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
}
