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
 * @Date 2020-06-14 17:33
 */
@Entity
@Table(name="t_comment")
@Getter
@Setter
@ToString
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;

    @ManyToOne
    private Blog blog;

    @OneToMany
    private List<Comment> replyComments=new ArrayList<>();

    @ManyToOne
    private Comment parentComment;

    public Comment() {
    }
}
