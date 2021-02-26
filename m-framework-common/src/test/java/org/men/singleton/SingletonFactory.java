package org.men.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/2/26 2:19 下午
 **/
public class SingletonFactory {

    static final AtomicReference<SingletonFactory> INSTANCE = new AtomicReference<>();

    static SingletonFactory instance = null;

    private SingletonFactory() throws InterruptedException {
        System.out.println("创建了对象");
        Thread.sleep(1000L);
    }

    public static final SingletonFactory getInstance() throws InterruptedException {
        for (; ; ) {
             instance = INSTANCE.get();
            if (null != instance) return instance;
            SingletonFactory singletonFactory = new SingletonFactory();
            INSTANCE.compareAndSet(null, singletonFactory);
            return INSTANCE.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(SingletonFactory.getInstance());
        for (int i = 0; i<10 ; i++ ) {
            Thread thread01 = new Thread(()->{
                try {
                    System.out.println(SingletonFactory.getInstance());
                } catch (InterruptedException e) {

                }
            });
            thread01.start();
        }
        System.out.println("结束");
    }

}
