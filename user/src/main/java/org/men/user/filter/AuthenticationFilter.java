package org.men.user.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.men.common.model.LoginUser;
import org.men.common.utils.CommonUtils;
import org.men.common.utils.EncryptDecodeStr;
import org.men.user.conf.RedisConfig;
import org.men.user.entity.JwtUser;
import org.men.user.entity.User;
import org.men.user.service.UserService;
import org.men.user.util.SpringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户账号的验证
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;


    /**
     * 显示调整登录接口
     * @param authenticationManager
     */
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setFilterProcessesUrl("/auth/login");
        this.authenticationManager = authenticationManager;

    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        // 从输入流中获取到登录的信息
        try {
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
            );
            return authenticate;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回 改为sessionId
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        //将用户id作为value sessionId作为key
        String sessionId =  saveKeyRedis(jwtUser.getName());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String, Object> map = new HashMap<>();
        String password = jwtUser.getPassword();
        jwtUser.setPassword("password");
        map.put("user",jwtUser);

        map.put("token", sessionId);
        JSONObject json = new JSONObject(map);
        writer.write(json.toString());
        jwtUser.setPassword(password);
    }

    private String saveKeyRedis(final String name) {

        StringRedisTemplate redisTemplate = (StringRedisTemplate) SpringUtils.getBean("stringRedisTemplate");
        UserService userService = (UserService) SpringUtils.getBean("userService");
        User byName = userService.findByName(name);
        RedisConfig  redisConfig = (RedisConfig) SpringUtils.getBean("redisConfig");
        Integer IncrementOld = byName.getLoginTimes()==null?0:byName.getLoginTimes();
        EncryptDecodeStr encryptDecodeStr = new EncryptDecodeStr(IncrementOld.toString());
        try {
            String  sessionId =  encryptDecodeStr.encrypt(byName.getId());
            String userId = redisTemplate.opsForValue().get(sessionId);
            if(StringUtils.isBlank(userId)){
                Integer Increment = byName.getLoginTimes()==null?0:byName.getLoginTimes()+1;
                byName.setLoginTimes(Increment);
                userService.save(byName);
                EncryptDecodeStr encryptDecodeNew = new EncryptDecodeStr(Increment.toString());
                sessionId = encryptDecodeNew.encrypt(byName.getId());
                redisTemplate.opsForValue().set(sessionId,byName.getId());
            }
            if(-1 != redisConfig.getSessionTimes()){
                redisTemplate.expire(sessionId, redisConfig.getSessionTimes(), TimeUnit.MINUTES);
            }else{
                //最多30天自动失效
                redisTemplate.expire(sessionId, 720, TimeUnit.HOURS);
            }
            return sessionId;
        }catch (Exception e){
            return "";
        }
    }

    // 这是验证失败时候调用的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        Map<String, Object> map = new HashMap<>();
        map.put("status",403);
        map.put("提示","用户名或者密码错误");
        JSONObject json = new JSONObject(map);
        response.getWriter().write(json.toString());
    }
}

