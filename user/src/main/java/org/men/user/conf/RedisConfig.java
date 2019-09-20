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

}
