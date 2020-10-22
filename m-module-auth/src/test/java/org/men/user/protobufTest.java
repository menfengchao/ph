package org.men.user;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;
import org.men.moduleauth.proto.Person;
import org.men.moduleauth.proto.PersonModel;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName protobufTest
 * @Description
 * @Author
 * @Date 2020/10/22 17:22
 * @Version V1.0.0
 **/
public class protobufTest {
    @Test
    public void testN() throws InvalidProtocolBufferException {
        //服务A
        long start = System.currentTimeMillis();
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        builder.setId(1);
        builder.setName("jihite我说 的");
        builder.setEmail("jihite@jihite.com");
        PersonModel.Person person = builder.build();
        byte[] byteArray = person.toByteArray();




        //服务B
        PersonModel.Person p2 = PersonModel.Person.parseFrom(byteArray);
        long end = System.currentTimeMillis()-start;
        System.out.println("time:" + end);
    }
}
