package org.men.sync;

import java.util.concurrent.CompletableFuture;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/7/8 9:44 上午
 **/
public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            return "hello world";
        });
        CompletableFuture future5 =  future.thenAcceptBoth(CompletableFuture.completedFuture("compose"),
                (x, y) -> System.out.println(x+y));//hello world compose
    }
}
