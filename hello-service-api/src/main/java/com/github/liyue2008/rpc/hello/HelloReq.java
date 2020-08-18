package com.github.liyue2008.rpc.hello;

import java.io.Serializable;

/**
 * 2020/8/18 下午4:13
 * aiguoxin
 * 说明:
 */
public class HelloReq implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
