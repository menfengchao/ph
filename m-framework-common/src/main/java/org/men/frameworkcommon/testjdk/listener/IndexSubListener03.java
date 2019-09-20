package org.men.frameworkcommon.testjdk.listener;

import lombok.extern.slf4j.Slf4j;
import org.men.frameworkcommon.testjdk.event.IndexSubEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName IndexSubListener03
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/9 11:15
 * @Version 1.0
 **/
@Component
@Slf4j
public class IndexSubListener03 {

    @EventListener
    @Async
    public void indexSubListener(IndexSubEvent indexSubEvent)
    {
        String  info = indexSubEvent.getInfo();
        log.info("嵌入任务03，消息主体：[{}]" ,info );
    }
}
