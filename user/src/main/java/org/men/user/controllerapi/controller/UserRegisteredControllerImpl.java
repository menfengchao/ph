package org.men.user.controllerapi.controller;

import io.swagger.annotations.ApiOperation;
import org.men.common.response.ResponseVO;
import org.men.user.controllerapi.UserRegisteredController;
import org.men.user.entity.User;
import org.men.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserRegisteredControllerImpl
 * @Description 用户登录之前控制器实现
 * @Author SuperMen
 * Date 2019/9/2 10:00
 * @Version 1.0
 **/
@RestController
public class UserRegisteredControllerImpl implements UserRegisteredController {

    @Autowired
    UserService userService;

    /**
     * 用户注册接口
     * @param user
     */
    @Override
    @PostMapping(value = "/auth/registered")
    public ResponseVO<User> registered(@RequestBody User user) {
        User userResult = userService.save(user);
        return new ResponseVO<>(userResult);
    }

    @Override
    @PostMapping(value = "/user/loginOut")
    public ResponseVO<Boolean> loginOut(@RequestHeader String token) {
        Boolean result = userService.loginOut(token);
        return new ResponseVO<>(result);
    }

}
