package org.men.common.testjdk.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.men.common.testjdk.event.IndexSubEvent;
import org.men.common.testjdk.service.IndexSubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @ClassName IndexSubServiceImpl
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/9 10:50
 * @Version 1.0
 **/
@Service
@Slf4j
public class IndexSubServiceImpl implements IndexSubService {

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void publishSubscription() {
       log.info("任务开始执行[{}]",01);

       log.info("执行嵌入逻辑开始");
       IndexSubEvent indexSubEvent = new IndexSubEvent(this,"消息传送");
       applicationContext.publishEvent(indexSubEvent);
       log.info("执行嵌入逻辑结束");

    }

}
