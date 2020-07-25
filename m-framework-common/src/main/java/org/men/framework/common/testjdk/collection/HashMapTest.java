package org.men.framework.common.testjdk.collection;

import java.util.HashMap;

/**
 * @ClassName HashMapTest
 * @Description TODO
 * @Author SuperMen
 * Date 2019/9/5 9:47
 * @Version 1.0
 **/
public class HashMapTest {

    public static void main(String[] args) {
        HashMap map = new HashMap();
        int i= 0;
        do {
            map.put("key"+i,"value01"+i);
            i++;
        }while (true);
    }


}
