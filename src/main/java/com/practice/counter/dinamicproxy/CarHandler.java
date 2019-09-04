package com.practice.counter.dinamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CarHandler implements InvocationHandler {
    private Object engine;
    private Object vehicle;

    public CarHandler(Object vehicle, Object engine) {
        this.vehicle = vehicle;
        this.engine = engine;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Handler: Invoking " + method.getName());
        Object result = null;
        try {
            if (method.getDeclaringClass().equals(IEngine.class)) {
                result = method.invoke(engine, args);
            } else if (method.getDeclaringClass().equals(IVehicle.class)) {
                result = method.invoke(vehicle, args);
            }
        }catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("Unexpected invocation exception: " + e.getMessage());
        }

        return result;
    }
}
