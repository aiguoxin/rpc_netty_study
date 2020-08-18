package com.github.liyue2008.rpc.hello;

import java.io.Serializable;

/**
 * 2020/8/18 下午4:13
 * aiguoxin
 * 说明:
 */
public class ApiResponse implements Serializable {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private String code;
    private Object data;

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
