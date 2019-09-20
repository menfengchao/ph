package org.men.moduleuser.conf;

import lombok.extern.slf4j.Slf4j;
import org.men.frameworkcommon.utils.IdWorker;
import org.men.moduleuser.entity.User;
import org.men.moduleuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName InitApplicationRunner
 * @Description 容器启动自动加载配置
 * @Author SuperMen
 * Date 2019/9/20 9:58
 * @Version 1.0
 **/
@Slf4j
public class InitApplicationRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        log.info("容器已启动");
        runAdmin();
    }

    public void runAdmin(){
        User admin = User.builder().id(IdWorker.getId())
                .password("123456")
                .source("1")
                .name("admin")
                .build();
        userService.save(admin);
    }




    /**
     * 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
     */
    @Autowired
    private RestTemplateBuilder builder;

    /**
     * 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

}
