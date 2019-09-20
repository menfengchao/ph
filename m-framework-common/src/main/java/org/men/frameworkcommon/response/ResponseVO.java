package org.men.frameworkcommon.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @ClassName ResponseVO
 * @Description 响应基础类
 * @Author SuperMen
 * Date 2019/8/30 16:29
 * @Version 1.0
 **/
@Data
public class ResponseVO<T> {
    private Integer status;

    private String msg;

    /**
     * 扩展字段（注解：如果为空的话不返回该字段）
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object extra;

    private T data;

    public ResponseVO() {
    }

    public ResponseVO(T data) {
        this.status = 200;
        this.msg = "success";
        this.data = data;
    }


    public ResponseVO(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResponseVO(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseVO(Integer status, String msg, T data, Object extra) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.extra = extra;
    }


}
