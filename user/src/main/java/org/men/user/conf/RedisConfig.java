package org.men.user.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/3 10:56
 * @Version 1.0
 **/
@Configuration
@Data
public class RedisConfig {

    public static final String TOKEN_HEADER = "token";


    @Value("${system.session-times}")
    public long sessionTimes;

    /**
     * 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
     */
    @Autowired
    private RestTemplateBuilder builder;

    /**
     * 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }
}
