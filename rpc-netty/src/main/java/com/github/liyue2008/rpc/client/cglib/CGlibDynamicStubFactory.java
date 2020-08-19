package com.github.liyue2008.rpc.client.cglib;

import com.github.liyue2008.rpc.client.StubFactory;
import com.github.liyue2008.rpc.transport.Transport;

/**
 * 2020/8/18 下午6:27
 * aiguoxin
 * 说明:
 */
public class CGlibDynamicStubFactory implements StubFactory {
    @Override
    public <T> T createStub(Transport transport, Class<T> serviceClass) {
        try {
            // jdk动态代理测试
            CGLibDynamicProxy proxy = new CGLibDynamicProxy(serviceClass);
            proxy.setTransport(transport);
            // 返回这个桩
            return (T) proxy.getProxy();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
