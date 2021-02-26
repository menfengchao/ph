package org.men.concurrent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.LockSupport;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/2/1 1:51 下午
 **/
public class LockSupportTest {


    public static void main(String[] args) {
        one();
    }

    public static void one() {
        FutureTask<String> task = new FutureTask<String>(new ThreadDome());
        Thread thread = new Thread(task);
        thread.start();
        try {
            Thread.sleep(5000L);
            LockSupport.unpark(thread);
            String s2 = task.get();
            System.out.println(s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
