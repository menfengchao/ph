package org.men.framework.common;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class BatchDeal {

    public static void main(String args[]) throws Exception {
        final String basePath = "C:\\Users\\Administrator\\Desktop\\ceshi.txt";
        File file = new File(basePath + "");
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        final LinkedBlockingQueue<String>[] queues = new LinkedBlockingQueue[5];
        queues[0] = new LinkedBlockingQueue(1);
        queues[1] = new LinkedBlockingQueue(1);
        queues[2] = new LinkedBlockingQueue(1);
        queues[3] = new LinkedBlockingQueue(1);
        queues[4] = new LinkedBlockingQueue(1);
        ExecutorService pool = Executors.newFixedThreadPool(5);
        AtomicBoolean isStop = new AtomicBoolean(false);
        for (int i = 0; i < 5; i++) {
            final int idx = i;
            pool.submit(new Runnable() {
                private LinkedBlockingQueue<String> queue = queues[idx];
                @Override
                public void run() {
                    while (!queue.isEmpty() ||  !isStop.get()) {
                        try {
                           sendMessage(queue.poll(5, TimeUnit.SECONDS));
                        } catch (Exception e) {
                        }
                    }
                }
            });
        }
        String line = null;
        int count = 0;
        while ((line = reader.readLine()) != null) {
            int idx = Math.abs(line.hashCode()) % 5;
            queues[idx].put(line);
            count++;
            System.out.println("已读取：" + count);
        }
        isStop.set(true);
        pool.shutdown();
        System.out.println("OVER");
    }

    private static void sendMessage(String message) throws Exception {
        if(message!=null){
            System.out.printf("deal thread:%s,message:%s \n",Thread.currentThread().getName(), message);
        }
    }
}

