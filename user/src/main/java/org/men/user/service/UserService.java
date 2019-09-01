package org.men.user.service;

import org.men.common.utils.CommonUtils;
import org.men.common.utils.IdWorker;
import org.men.user.dao.jpa.UserRepository;
import org.men.user.entity.JwtUser;
import org.men.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserService  implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User findById(final String id) {
        Optional<User> byIdUser = userRepository.findById(id);
        if(byIdUser.isPresent())
            return byIdUser.get();
        return null;
    }

    public User save(User user) {
        user.setId(IdWorker.getId());
        user.setCreateTime(CommonUtils.getStringDate(new Date()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByName(s);
        return new JwtUser(user);
    }
}
