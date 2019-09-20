package org.men.frameworkcommon.testjdk.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @ClassName IndexSubEvent
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/9 10:55
 * @Version 1.0
 **/
@Data
public class IndexSubEvent extends ApplicationEvent {

    private String info;


    public IndexSubEvent(final Object source,String info) {
        super(source);
        this.info = info;
    }

}
