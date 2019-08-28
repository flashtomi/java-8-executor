package com.practice.counter.dinamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GenericLogger implements InvocationHandler {
    private Object target;

    public GenericLogger(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Generic Logger Entry: Invoking " +
                method.getName());
        return method.invoke(target, args);
    }
}
