package com.practice.counter.dinamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomMock implements InvocationHandler {

    private Object targetObject;

    public <T> CustomMock(T obj) {
        targetObject = obj;
    }

    @SuppressWarnings("unchecked")
    public static <T> T createMock(T obj) {
        return (T) java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
                .getClass().getInterfaces(), new CustomMock(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Mocking object");
        return method.invoke(targetObject, args);
    }
}
