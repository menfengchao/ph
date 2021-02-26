package org.men.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.LockSupport;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/2/1 2:03 下午
 **/
public class ThreadDome implements Callable<String> {
    @Override
    public String call() throws Exception {
        LockSupport.park();
        System.out.println("已执行");
        return Thread.currentThread().getName();
    }
}
