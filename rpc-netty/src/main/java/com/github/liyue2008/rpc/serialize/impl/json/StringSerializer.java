package com.github.liyue2008.rpc.serialize.impl.json;

import com.alibaba.fastjson.JSON;
import com.github.liyue2008.rpc.serialize.Serializer;
import com.github.liyue2008.rpc.serialize.impl.Types;

import java.nio.charset.StandardCharsets;

/**
 * 2020/8/18 上午10:46
 * aiguoxin
 * 说明:
 * /var/folders/5_/dkz6wc1x2z3b8p2gs5wcpggr0000gn/T/simple_rpc_name_service.data
 * dH{"com.github.liyue2008.rpc.hello.HelloService":["rpc://localhost:9999"]}%
 */
public class StringSerializer implements Serializer<String> {

    @Override
    public int size(String entry) {
        String json = JSON.toJSONString(entry);
        return json.getBytes(StandardCharsets.UTF_8).length;
    }

    @Override
    public void serialize(String entry, byte[] bytes, int offset, int length) {
        String json = JSON.toJSONString(entry);
        byte [] strBytes = json.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(strBytes, 0, bytes, offset, strBytes.length);
    }

    @Override
    public String parse(byte[] bytes, int offset, int length) {
        String val =  new String(bytes, offset, length, StandardCharsets.UTF_8);
        return JSON.parseObject(val,String.class);
    }

    @Override
    public byte type() {
        return Types.TYPE_STRING;
    }

    @Override
    public Class<String> getSerializeClass() {
        return String.class;
    }
}
