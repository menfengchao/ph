package org.men.moduleauth.filter;

import org.men.moduleauth.conf.RedisConfig;
import org.men.moduleauth.entity.User;
import org.men.moduleauth.service.UserService;
import org.men.moduleauth.util.SpringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * 进行鉴权
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader(RedisConfig.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null) {
            chain.doFilter(request, response);
            return;
        }
        StringRedisTemplate redisTemplate = (StringRedisTemplate) SpringUtils.getBean("stringRedisTemplate");
        String userId = redisTemplate.opsForValue().get(tokenHeader);
        if (userId == null) {
            response.setStatus(401);
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(userId));
        super.doFilterInternal(request, response, chain);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String userId) {
        UserService userService = (UserService) SpringUtils.getBean("userService");
        User byId = userService.findById(userId);
        if (byId != null) {
            return new UsernamePasswordAuthenticationToken(byId.getName(), null,
                    Collections.singleton(new SimpleGrantedAuthority(byId.getRole()))
            );
        }
        return null;
    }

}
