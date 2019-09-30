package org.men.moduleauth.controllerapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.men.frameworkcommon.response.ResponseVO;
import org.men.moduleauth.entity.User;

/**
 * @ClassName UserController
 * @Description 用户相关接口
 * @Author SuperMen
 * Date 2019/8/30 16:17
 * @Version 1.0
 **/
@Api(value = "用户类控制器", tags = "用户类控制器")
public interface UserController {

    @ApiOperation(value = "获取用户", notes = "获取用户")
    ResponseVO<User> findById(String id);

    @ApiOperation(value = "保存用户", notes = "保存用户")
    ResponseVO<User> save(User user);

    @ApiOperation(value = "删除用户", notes = "删除用户")
    ResponseVO<Boolean> delete(String id);
}
