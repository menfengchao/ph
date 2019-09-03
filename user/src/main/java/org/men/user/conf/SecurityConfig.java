package org.men.user.conf;


import org.men.common.execption.MyAuthenticationEntryPoint;
import org.men.user.filter.AuthenticationFilter;
import org.men.user.filter.AuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * https://blog.csdn.net/ech13an/article/details/80779973
 * @Author superMen
 * @Date 2019/9/2 14:55
 * @Method
 * @Param 
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    // 因为UserDetailsService的实现类实在太多啦，这里设置一下我们要注入的实现类
    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailsService;

    // 加密密码的，安全第一嘛~
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManagerBuilder（身份验证管理生成器）
     * @Date 2019/9/2 15:01
     * @Method configure
     * @Param [auth]
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * WebSecurity（WEB安全）
     * @Date 2019/9/2 15:00
     * @Method configure
     * @Param [web]
     */
    @Override
    public void configure(final WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * HttpSecurity（HTTP请求安全处理）
     * @Date 2019/9/2 15:01
     * @Method configure
     * @Param [http]
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                //允许跨域请求
                .csrf().disable()
                .authorizeRequests()
                // 测试用资源，需要验证了的用户才能访问
                .antMatchers("/user/**").authenticated()
                // 需要角色为ADMIN才能删除该资源  接口上配合注释
                .antMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
                //.hasAuthority("ADMIN")
                // 其他都放行了
                .anyRequest().permitAll()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager()))
                .addFilter(new AuthorizationFilter(authenticationManager()))
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 加一句异常处理
                .exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint())

                //以下为附加配置
                .and()
                //通过formlogin方法登录，并设置登录url为/api/user/login
               .formLogin().loginPage("/auth/login")
                //指定登录成功后跳转到/index页面 不需要 基于token认证
                //.defaultSuccessUrl("/index")
                //指定登录失败后跳转到/login?error页面 不需要 基于token认证
                //.failureUrl("/login?error")
                .permitAll()
                .and()
                //开启cookie储存用户信息，并设置有效期为14天，指定cookie中的密钥
                //.rememberMe().tokenValiditySeconds(1209600).key("mykey")
                .logout()
                //指定登出的url
                .logoutUrl("/auth/logout");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}