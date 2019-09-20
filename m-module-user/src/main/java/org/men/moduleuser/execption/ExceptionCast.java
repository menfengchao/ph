package org.men.moduleuser.execption;


import org.men.moduleuser.execption.CustomException;

/**
 * @ClassName ExceptionCast
 * @Description 异常抛出封装
 * @Author SuperMen
 * Date 2019/8/9 9:49
 * @Version 1.0
 **/
public class ExceptionCast {

    public static void cast(int code, String msg) {
        throw new CustomException(code, msg);
    }

}
