package org.men.framework.common;

import java.util.concurrent.*;

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        int threadNum = Runtime.getRuntime().availableProcessors();
        System.out.printf("core thread num：%s \n",threadNum);
        ExecutorService executor = new ThreadPoolExecutor(threadNum,
                threadNum,
                10L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        HoldLockThread threadA = new HoldLockThread(lockA, lockB);
        HoldLockThread threadB = new HoldLockThread(lockB, lockA);
        FutureTask taskA = new FutureTask(threadA);
        FutureTask taskB = new FutureTask(threadB);
        executor.execute(taskA);
        executor.execute(taskB);
        try {
            Object resultA = taskA.get();
            Object resultB = taskB.get();
            System.out.printf("end... %s,%s" ,resultA.toString(),resultB.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}

class HoldLockThread implements Callable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public Object call() throws Exception {
        System.out.printf("call：%s \n",Thread.currentThread().getName());
        synchronized (lockA){
            System.out.printf("%s: HolaLockThread.call lockA \n",Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
            synchronized(lockB){
                System.out.printf("%s: HolaLockThread.call lockB \n",Thread.currentThread().getName());
                return "name："+Thread.currentThread().getName();
            }
        }
    }
}