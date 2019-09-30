package org.men.moduleauth.dao.jpa;

import org.men.moduleauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserRepository
 * @Description user Jpa dao
 * @Author SuperMen
 * Date 2019/8/30 16:26
 * @Version 1.0
 **/
public interface UserRepository extends JpaRepository<User, String> {

    User findByName(String username);

}
