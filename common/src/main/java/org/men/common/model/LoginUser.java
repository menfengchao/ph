package org.men.common.model;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName LoginUser
 * @Description 登录model
 * @Author SuperMen
 * Date 2019/9/2 11:46
 * @Version 1.0
 **/
@Data
@ToString
public class LoginUser {
    private String username;
    private String password;
    private Integer rememberMe;
}
