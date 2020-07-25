package org.men.moduleauth.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.men.framework.common.model.LoginUser;
import org.men.framework.common.utils.EncryptDecodeStr;
import org.men.moduleauth.conf.RedisConfig;
import org.men.moduleauth.entity.JwtUser;
import org.men.moduleauth.entity.User;
import org.men.moduleauth.service.UserService;
import org.men.moduleauth.util.SpringUtils;
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
     *
     * @param authenticationManager
     */
    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setFilterProcessesUrl("/auth/login");
        this.authenticationManager = authenticationManager;

    }

    /**
     * 登录认证方法
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
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
        String sessionId = saveKeyRedis(jwtUser.getName());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String, Object> map = new HashMap<>();
        String password = jwtUser.getPassword();
        jwtUser.setPassword("password");
        map.put("user", jwtUser);
        map.put("token", sessionId);
        JSONObject json = new JSONObject(map);
        writer.write(json.toString());
        jwtUser.setPassword(password);
    }

    private String saveKeyRedis(final String name) {
        StringRedisTemplate redisTemplate = (StringRedisTemplate) SpringUtils.getBean("stringRedisTemplate");
        UserService userService = (UserService) SpringUtils.getBean("userService");
        //1:登录成功后 通过用户名获取用户 上次登录加密字符串 及用户id
        User byName = userService.findByName(name);
        //首次登录随便给个加密字符串 查询不到 自然设置新的
        Integer IncrementOld = byName.getLoginTimes() == null ? 0 : byName.getLoginTimes();
        EncryptDecodeStr encryptDecodeStr = new EncryptDecodeStr(IncrementOld.toString());
        try {
            //1.1 根据用户id 和 上次机密字符串算出 上次 sessionId 查看上次sessionId是否存在
            String sessionId = encryptDecodeStr.encrypt(byName.getId());
            //1.2查看上次sessionId是否存在
            String userId = redisTemplate.opsForValue().get(sessionId);
            //1.3 如果不存在 生成sessionId
            if (StringUtils.isBlank(userId)) {
                //2:生成随机加密字符串
                Integer Increment = (int) ((Math.random() * 9 + 1) * 100000);
                byName.setLoginTimes(Increment);
                //2.1保存加密字符串第一步使用
                userService.save(byName);
                //2.2 userId与加密字符串生成sessionId
                EncryptDecodeStr encryptDecodeNew = new EncryptDecodeStr(Increment.toString());
                sessionId = encryptDecodeNew.encrypt(byName.getId());
                //2.3 将 sessionId 作为key userId 作为value存入redis
                redisTemplate.opsForValue().set(sessionId, byName.getId());
            }
            //3:根据配置获取sessionId有效时间  无论是使用新生成的sessionId 还是没有过期的sessionId 都重新设置有效期
            RedisConfig redisConfig = (RedisConfig) SpringUtils.getBean("redisConfig");
            if (-1 != redisConfig.getSessionTimes()) {
                redisTemplate.expire(sessionId, redisConfig.getSessionTimes(), TimeUnit.MINUTES);
            } else {
                //最多30天自动失效
                redisTemplate.expire(sessionId, 720, TimeUnit.HOURS);
            }
            return sessionId;
        } catch (Exception e) {
            e.printStackTrace();
            return "设置sessionId失败";
        }
    }

    // 这是验证失败时候调用的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        Map<String, Object> map = new HashMap<>();
        map.put("status", 403);
        map.put("提示", "用户名或者密码错误");
        JSONObject json = new JSONObject(map);
        response.getWriter().write(json.toString());
    }
}

