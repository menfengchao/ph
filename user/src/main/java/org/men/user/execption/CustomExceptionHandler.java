package org.men.user.execption;

import lombok.extern.slf4j.Slf4j;
import org.men.common.response.ResponseVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 全局异常处理控制器
 *
 * @author youngsapling
 * @date 2019-04-08
 */
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseVO customException(CustomException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return new ResponseVO(e.getStatus(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseVO exception(Exception e) {
       log.error(errInfo(e));
        e.printStackTrace();
        return new ResponseVO(500, e.getMessage());
    }

    private static String errInfo(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
