package org.men.mmodulearticle.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 优雅的UI界面拦截配置映射  viboot-swagger2
     * https://doc.xiaominfo.com/guide/springfox-swagger.html
     *
     * @param registry
     */
    @Override
    @Profile(value = {"dev", "test"})
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler添加映射路径，然后通过addResourceLocations来指定路径
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}