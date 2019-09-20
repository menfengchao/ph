package org.men.moduleuser.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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
