package org.men.user.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.men.common.model.LoginUser;
import org.men.common.utils.CommonUtils;
import org.men.user.entity.JwtUser;
import org.men.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 用户账号的验证
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

//    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${system.session-times}")
    long sessionTimes;

    /**
     * 显示调整登录接口
     * @param authenticationManager
     */
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setFilterProcessesUrl("/auth/login");
        this.authenticationManager = authenticationManager;

    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        // 从输入流中获取到登录的信息
        try {
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
//            rememberMe.set(loginUser.getRememberMe());
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

        //jjwt 存在过期时间刷新及退出登录问题不好处理 改为spring session
//        boolean isRemember = rememberMe.get() == 1;
//        String role = "";
//        // 因为在JwtUser中存了权限信息，可以直接获取，由于只有一个角色就这么干了
//        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
//        for (GrantedAuthority authority : authorities){
//            role = authority.getAuthority();
//        }
//        // 根据用户名，角色创建token
//        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), role, isRemember);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
//        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
//        map.put("token", JwtTokenUtils.TOKEN_PREFIX + token);

        JwtUser jwtUserReslt = newUserSession(jwtUser);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String, Object> map = new HashMap<>();
        String password = jwtUser.getPassword();
        jwtUser.setPassword("password");
        map.put("user",jwtUser);

        map.put("token", jwtUserReslt.getSessionId());
        JSONObject json = new JSONObject(map);
        writer.write(json.toString());
        jwtUser.setPassword(password);
    }


    public JwtUser newUserSession(JwtUser user) {
        //组装Session并保存
        JwtUser session = getUserSessionByUserId(user.getId());
        if(Objects.isNull(session)){
            session = new JwtUser();
            String sessionId = CommonUtils.uuid();
            session.setSessionId(sessionId);
            //设置用户实体属性
            session.setId(user.getId());
            session.setName(user.getName());
        }
        saveUserSession(session);
        return session;
    }


    public JwtUser getUserSessionByUserId(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            String sessionId = redisTemplate.boundValueOps(userId).get();
            if(StringUtils.isNotBlank(sessionId)){
                String sessionStr = redisTemplate.boundValueOps(sessionId).get();
                return JSON.parseObject(sessionStr, new TypeReference<JwtUser>() { });
            }
        }
        return null;
    }


    public void saveUserSession(JwtUser userSession) {
        String sessionId = userSession.getSessionId();
        String userId = userSession.getId();

        //构建一个以sessionId为key的string类型的数据
        redisTemplate.boundValueOps(sessionId).set(JSON.toJSONString(userSession));

        //设置以sessionId为key的数据过期时间
        if(-1 != sessionTimes){
            redisTemplate.expire(sessionId, sessionTimes, TimeUnit.MINUTES);
        }

        //构建一个以userId为key的string类型的数据，以便查询，否则不知道userId和session数据的对应关系
        redisTemplate.boundValueOps(userId).set(sessionId);

        //设置以userId为key的数据过期时间
        if(-1 != sessionTimes){
            redisTemplate.expire(userId, sessionTimes, TimeUnit.MINUTES);
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

