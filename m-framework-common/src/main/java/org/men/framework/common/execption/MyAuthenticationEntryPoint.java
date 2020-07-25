package org.men.framework.common.execption;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.men.framework.common.response.ResponseVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName MyAuthenticationEntryPoint
 * @Description JWT 403 统一异常处理
 * 没有登录权限统一处理 不包括访问权限控制
 * @Author SuperMen
 * Date 2019/9/2 14:03
 * @Version 1.0
 **/
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ResponseVO result = new ResponseVO();
        result.setStatus(response.getStatus());
        if(response.getStatus() == 401 ){
            result.setMsg("登录失效 请您重新登录");
        }else if(response.getStatus() == 403){
            result.setMsg("权限不知道去哪了，请联系管理员");
        }else {
            result.setMsg("内部错误，请稍后重试");
        }
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
    }

}
