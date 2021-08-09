package org.men.function;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author mfc
 * @version v1.0
 * @date 2021/3/11 9:21 上午
 **/
public class FinalTest {

    public static void main(String[] args) {
//        int i ;
//        try {
//             i = 1;
//            System.out.println(i);
//            return;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            i = 2;
//            System.out.println(i);
//        }
        TreeSet<BiFunctionTest.User> set = new TreeSet();
        List<BiFunctionTest.User> list = new ArrayList<>();
        BiFunctionTest.User user1 = new BiFunctionTest.User();
        user1.setName("100");
        user1.setAge(1);
        BiFunctionTest.User user2 = new BiFunctionTest.User();
        user2.setName("99");
        user2.setAge(2);
        BiFunctionTest.User user3 = new BiFunctionTest.User();
        user3.setName("99");
        user3.setAge(3);
        BiFunctionTest.User user4 = new BiFunctionTest.User();
        user4.setName("99");
        user4.setAge(3);

//        set.add(user4);
//        set.add(user2);
//        set.add(user1);
//        set.add(user3);

        list.add(user4);
        list.add(user2);
        list.add(user1);
        list.add(user3);

        list.sort(new Comparator<BiFunctionTest.User>() {
            @Override
            public int compare(BiFunctionTest.User o1, BiFunctionTest.User o2) {
                int i =  o1.getName().compareTo(o2.getName());
                System.out.println(i);
                if(i == 0)
                    return  o2.getAge() - o1.getAge() ;
                return i;
            }
        });
        System.out.println(list);
    }
}
