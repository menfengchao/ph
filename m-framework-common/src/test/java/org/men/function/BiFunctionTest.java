package org.men.function;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/4/13 10:31 上午
 **/
public class BiFunctionTest {

    public static void main(String[] args) throws Exception {
//        BiFunctionTest test = new BiFunctionTest();
//        //实现四则运算
//        System.out.println(test.compute(4,2,(value1,value2)->value1+value2));
//        System.out.println(test.compute(4,2,(v1,v2)->v1-v2));
//        System.out.println(test.compute(1,2,(v1,v2)->v1*v2));
//        System.out.println(test.compute(3,2,(v1,v2)->v1/v2));
//        System.out.println(test.calcute(4,2,(v1,v2)->v1+v2,v->v * v));
         syncTest();


    }

    public  int compute(int num1, int num2, BiFunction<Integer,Integer,Integer> biFunction){
        return  biFunction.apply(num1,num2);
    }

    public int calcute(int num1, int num2, BiFunction<Integer,Integer,Integer> biFunction, Function<Integer,Integer> function){
        //调用addThen首先对接收的两个参数进行bifunction的apply，然后在进行function的apply
        BiFunction<Integer, Integer, Integer> integerIntegerIntegerBiFunction = biFunction.andThen(function);
        return integerIntegerIntegerBiFunction.apply(num1,num2);
    }

    private static void syncTest() throws Exception {
        long scoreStart = System.currentTimeMillis();
        CompletableFuture<Integer> resultOne = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 100;
        });
        CompletableFuture<Integer> resultTwo = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;
        });
        Integer integer = resultOne.get(11000, TimeUnit.MILLISECONDS);
        System.out.println(integer);
        Integer integer1 = resultTwo.get();
        System.out.println(integer1);
        System.out.println("耗时:"+ (System.currentTimeMillis() - scoreStart) + "ms");
    }

    /**
     * @author mfc
     * @version v1.0
     * @date 2021/3/11 9:27 上午
     **/
    public static class User{

        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
