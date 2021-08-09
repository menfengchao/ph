package org.men.framework.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 线程池工厂类
 *
 * @author mfc
 * @version v1.0
 * @date 2021/4/16 6:02 下午
 **/
public class ThreadPoolFactory {

    private static final Logger log = LoggerFactory.getLogger(ThreadPoolFactory.class);

    /**
     * 公用线程池大小
     **/
    public static Integer commonJobSize = 8;

    /**
     * 公用线程池名称
     **/
    public static final String POOL_COMMON_JOB_USE_ONLY = "commonJobPool";


    private static Map<String, ThreadPoolExecutor> all = new HashMap();


    static {
        init();
        create(commonJobSize, POOL_COMMON_JOB_USE_ONLY);
    }

    public static void init() {
    }

    /**
     * <p>功能描述：创建有界队列的线程池</p>
     *
     * @param num
     * @param name
     * @param queueLength
     * @return
     */
    public static synchronized ThreadPoolExecutor create(int num, final String name, int queueLength) {
        ThreadFactory threadFactory = new ThreadFactory() {
            int i = 1;

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "Cool-ThreadPool-" + name + "-thread-" + i++);
                return t;
            }
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(num, num, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(queueLength), threadFactory);
        all.put(name, executor);
        return executor;
    }

    /**
     * <p> 创建普通线程池 </p>
     *
     * @param num  线程数量
     * @param name 名称
     * @return
     */
    public static synchronized ThreadPoolExecutor create(int num, final String name) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(num,
                new ThreadFactory() {
                    int i = 1;

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r, "Cool-ThreadPool-" + name + "-thread-" + i++);
                        return t;
                    }
                });
        all.put(name, executor);
        return executor;
    }

    /**
     * <p> 创建线程池，支持异常处理 </p>
     *
     * @param num              线程数量
     * @param name             名称
     * @param exceptionHandler 默认异常处理
     * @return
     */
    public static synchronized ThreadPoolExecutor create(int num, final String name,
                                                         final Thread.UncaughtExceptionHandler exceptionHandler) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(num,
                new ThreadFactory() {
                    int i = 1;

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r, "Cool-ThreadPool-" + name + "-thread-" + i++);
                        t.setUncaughtExceptionHandler(exceptionHandler);
                        return t;
                    }
                });
        all.put(name, executor);
        return executor;

    }

    /**
     * 获取一个已经存在的线程池或null
     *
     * @param name
     * @return
     */
    public static ThreadPoolExecutor get(String name) {
        return all.get(name);
    }

}
