package org.men.moduleauth.controllerapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.men.framework.common.response.ResponseVO;
import org.men.moduleauth.entity.User;

/**
 * @ClassName UserRegisteredController
 * @Description 用户登录之前控制器
 * @Author SuperMen
 * Date 2019/9/2 9:59
 * @Version 1.0
 **/
@Api(value = "用户登录之前控制器", tags = "用户登录之前控制器")
public interface UserRegisteredController {

    @ApiOperation(value = "注册用户", notes = "/auth/registered 用户登录 /auth/login")
    ResponseVO<User> registered(User user);

    @ApiOperation(value = "用户登出", notes = "/user/loginOut")
    ResponseVO<Boolean> loginOut(String token);

}
