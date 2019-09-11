package com.practice.counter.dinamicproxy;

import java.lang.reflect.Proxy;

public class MockFactory {
    public static Object createMock(Object object, CustomMock mock) {

        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object
                .getClass().getInterfaces(), mock);
    }
}
