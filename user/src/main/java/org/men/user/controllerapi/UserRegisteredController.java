package org.men.user.controllerapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.men.common.response.ResponseVO;
import org.men.user.entity.User;

/**
 * @ClassName UserRegisteredController
 * @Description 用户登录之前控制器
 * @Author SuperMen
 * Date 2019/9/2 9:59
 * @Version 1.0
 **/
@Api(value = "用户登录之前控制器",tags="注册接口")
public interface UserRegisteredController {

    @ApiOperation(value = "注册用户",notes = "/auth")
    ResponseVO<User> registered(User user);

    @ApiOperation(value = "用户登出",notes = "/auth")
    ResponseVO<Boolean> loginOut( String token);
}
