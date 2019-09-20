package org.men.moduleuser.controllerapi.controller;

import org.men.frameworkcommon.response.ResponseVO;
import org.men.moduleuser.controllerapi.UserRegisteredController;
import org.men.moduleuser.entity.User;
import org.men.moduleuser.service.UserService;
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
     *
     * @param user
     */
    @Override
    @PostMapping(value = "/auth/registered")
    public ResponseVO<User> registered(@RequestBody User user) {
        User userResult = userService.save(user);
        try {
            Thread.sleep(10000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseVO<>(userResult);
    }

    @Override
    @PostMapping(value = "/server/user/loginOut")
    public ResponseVO<Boolean> loginOut(@RequestHeader String token) {
        Boolean result = userService.loginOut(token);
        return new ResponseVO<>(result);
    }

}
