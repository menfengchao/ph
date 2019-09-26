package org.men.mmodulearticle.core.utils;

/**
 * @ClassName BeanTools
 * @Description 根据Class获取对象实例
 * @Author SuperMen
 * Date 2019/9/26 10:33
 * @Version 1.0
 **/
public class BeanTools {

    public static Object getBaean(final Class aClass) {
        try {
            //创建指定类型的javabean对象
            Object o = aClass.newInstance();
            return o;
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
