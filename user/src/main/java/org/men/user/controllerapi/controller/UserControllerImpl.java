package org.men.user.controllerapi.controller;

import org.men.common.response.ResponseVO;
import org.men.user.controllerapi.UserController;
import org.men.user.entity.User;
import org.men.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserControllerImpl
 * @Description User对外接口实现
 * @Author SuperMen
 * Date 2019/8/30 16:19
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    @Override
    @GetMapping(value = "/findById")
    public ResponseVO<User> findById(@RequestParam("id") String id) {
        User user = userService.findById(id);
        return new ResponseVO<>(user);
    }

    @Override
    @PostMapping(value = "/save")
    public ResponseVO<User> save(@RequestBody User user) {
        User resultUser = userService.save(user);
        return new ResponseVO<>(resultUser);
    }
}
