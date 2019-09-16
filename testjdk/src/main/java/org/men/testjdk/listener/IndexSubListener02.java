package org.men.testjdk.listener;

import lombok.extern.slf4j.Slf4j;
import org.men.testjdk.event.IndexSubEvent;
import org.men.testjdk.service.impl.IndexSubServiceImpl;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName IndexSubListener02
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/9 11:04
 * @Version 1.0
 **/
@Component
@Slf4j
public class IndexSubListener02  implements SmartApplicationListener {


    //监听事件的类型
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == IndexSubEvent.class;
    }

    //监听的发生地
    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return aClass == IndexSubServiceImpl.class;
    }


    @Override
    @Async
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //转换事件类型
        IndexSubEvent userRegisterEvent = (IndexSubEvent) applicationEvent;
        //获取注册用户对象信息
        String  info = userRegisterEvent.getInfo();
        //.../完成注册业务逻辑

        log.info("嵌入任务02，消息主体：[{}]" ,info );

    }

    /**
     * return 的数值越小证明优先级越高，执行顺序越靠前。
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}