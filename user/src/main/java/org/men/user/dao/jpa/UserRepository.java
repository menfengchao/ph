package org.men.user.dao.jpa;

import org.men.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserRepository
 * @Description TODO
 * @Author SuperMen
 * Date 2019/8/30 16:26
 * @Version 1.0
 **/
public interface UserRepository extends JpaRepository<User,String> {
}
