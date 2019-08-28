package com.practice.counter.dinamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CarHandler implements InvocationHandler {
    private ICar ICar;

    public CarHandler(ICar ICar) {
        this.ICar = ICar;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("ICar Handler: Invoking " +
                method.getName());
        return method.invoke(ICar, args);
    }
}
