package org.men.moduleuser.controllerapi.controller;

import org.men.frameworkcommon.response.ResponseVO;
import org.men.moduleuser.controllerapi.UserController;
import org.men.moduleuser.entity.User;
import org.men.moduleuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserControllerImpl
 * @Description User对外接口实现
 * @Author SuperMen
 * Date 2019/8/30 16:19
 * @Version 1.0
 **/
@RestController
@RequestMapping("/server/user")
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

    @Override
    @DeleteMapping(value = "delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseVO<Boolean> delete(@RequestParam(name = "id", value = "id") String id) {
        Boolean result = userService.delete(id);
        return new ResponseVO<>(result);
    }
}
