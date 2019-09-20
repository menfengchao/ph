package org.men.moduleuser.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName User
 * @Description 用户模块 分为浏览管理员 和 授权登录模块的人员信息
 * @Author SuperMen
 * Date 2019/8/30 16:13
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Builder
@Table(name = "u_user")
public class User implements Serializable {
    /**
     * 用户id
     */
    @Id
    private String id;
    /**
     * 用户姓名
     */
    @Column(name = "name")
    private String name;
    /**
     * 用户电话
     */
    @Column(name = "phone")
    private String phone;
    /**
     * 用户来源
     */
    @Column(name = "source")
    private String source;
    /**
     * 用户创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "login_times")
    private Integer LoginTimes;
}
