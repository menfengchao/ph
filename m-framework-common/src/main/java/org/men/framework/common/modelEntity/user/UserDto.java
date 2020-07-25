package org.men.framework.common.modelEntity.user;


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
public class UserDto implements Serializable {
    /**
     * 用户id
     */
    private String id;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 用户来源
     */
    private String source;
    /**
     * 用户创建时间
     */
    private String createTime;


    private String role;

    private Integer LoginTimes;
}
