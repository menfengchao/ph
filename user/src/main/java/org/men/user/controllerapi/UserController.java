package org.men.user.controllerapi;

import org.men.common.response.ResponseVO;
import org.men.user.entity.User;

/**
 * @ClassName UserController
 * @Description 用户对外接口
 * @Author SuperMen
 * Date 2019/8/30 16:17
 * @Version 1.0
 **/
public interface UserController {

    ResponseVO<User> findById(String id);

    ResponseVO<User> save(User user);
}
