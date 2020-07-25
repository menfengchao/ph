package org.men.servereureka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerEurekaApplicationTests {

    @Test
    public void contextLoads() {
    }

    public static void main(String[] args) {
        ColnoObject colno = new ColnoObject();
        colno.setName("刘超");
        ColnoObject clone2 =  colno.clone();
        clone2.setName("张超");
        System.out.println(colno.getName());
        System.out.println(clone2.getName());
    }

}
