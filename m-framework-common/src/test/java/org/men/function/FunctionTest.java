package org.men.function;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/3/10 9:18 上午
 **/
public class FunctionTest {
    public static void main(String[] args) {
        FunctionTest test = new FunctionTest();
        BiFunction<Integer, Integer, Integer> function3 = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> integerIntegerIntegerBiFunction = function3.andThen(a -> a * a);
        System.out.println(test.compute(2, integerIntegerIntegerBiFunction));

        Integer apply2 = integerIntegerIntegerBiFunction.apply(2, 2);
        System.out.println(apply2);
        // 普通定义
        Function<Integer, Integer> function = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer o) {
                return o * o;
            }
        };
        Function<Integer, String> function1 = function.andThen(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "result is " + integer;
            }
        });

        String apply = function1.apply(2);
        System.out.println(apply);
        // 用lambda的方式实现
        Function<Integer, Integer> lambdaFunc = o -> o * o;
        Function<Integer, String> function2 = lambdaFunc.andThen(o -> "result is" + o);
        Function<Object, Integer> compose = lambdaFunc.compose(o -> (Integer) o);
        String apply1 = function2.apply(2);
        Integer apply3 = compose.apply(2);
        System.out.println(apply1);
        System.out.println(apply3);
    }

    public int compute(int a, BiFunction<Integer, Integer, Integer> function) {
        return function.apply(a, 1);
    }

}
