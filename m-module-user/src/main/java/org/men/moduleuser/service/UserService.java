package org.men.moduleuser.service;

import org.apache.commons.lang3.StringUtils;
import org.men.frameworkcommon.utils.CommonUtils;
import org.men.frameworkcommon.utils.IdWorker;
import org.men.frameworkcommon.utils.UpdateUtils;
import org.men.moduleuser.dao.jpa.UserRepository;
import org.men.moduleuser.entity.JwtUser;
import org.men.moduleuser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author SuperMen
 * Date 2019/8/30 16:33
 * @Version 1.0
 **/
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User findById(final String id) {
        Optional<User> byIdUser = userRepository.findById(id);
        if (byIdUser.isPresent())
            return byIdUser.get();
        return null;
    }

    public User findByName(final String name) {
        return userRepository.findByName(name);
    }


    public User save(User registerUser) {

        if (StringUtils.isEmpty(registerUser.getId())) {
            User user = new User();
            user.setId(IdWorker.getId());
            user.setName(registerUser.getName());
            // 记得注册的时候把密码加密一下
            user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
            user.setRole("ROLE_USER");
            user.setPhone(registerUser.getPhone());
            user.setSource(registerUser.getSource());
            user.setCreateTime(CommonUtils.getStringDate(new Date()));
            return userRepository.save(user);
        } else {
            User oldUser = findById(registerUser.getId());
            UpdateUtils.copyNonNullProperties(oldUser, registerUser);
            return userRepository.save(registerUser);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByName(s);
        return new JwtUser(user);
    }

    public Boolean delete(final String id) {
        userRepository.deleteById(id);
        return true;
    }

    public Boolean loginOut(String token) {
        Boolean delete = redisTemplate.delete(token);
        return delete;
    }
}
