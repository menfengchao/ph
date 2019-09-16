package org.men.testjdk.listener;


import lombok.extern.slf4j.Slf4j;
import org.men.testjdk.event.IndexSubEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName IndexSubListener
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/9 10:57
 * @Version 1.0
 **/
@Component
@Slf4j
@Async
public class IndexSubListener implements ApplicationListener<IndexSubEvent> {
    @Override
    public void onApplicationEvent(final IndexSubEvent indexSubEvent) {
        log.info("嵌入任务01");
    }
}
