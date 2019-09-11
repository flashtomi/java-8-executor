package com.practice.counter.dinamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class CustomMock implements InvocationHandler {

    private Object originalObject;
    public ArrayList functionCalls = new ArrayList();
    public HashMap exceptions = new HashMap();

    public CustomMock(Object originalObject)
    {
        this.originalObject = originalObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        FunctionCall functionCall = new FunctionCall();
        functionCall.name = method.getName();
        functionCall.arguments = args;
        if (exceptions.containsKey(method.getName())) {
            Throwable throwable = (Throwable) exceptions.get(method.getName());
            functionCall.thrownException = throwable;
            functionCalls.add(functionCall);
            throw throwable;
        }
        Object returnValue = null;
        try {
            returnValue = method.invoke(originalObject, args);
        }
        catch (InvocationTargetException e) {
            functionCall.thrownException = e.getTargetException();
            functionCalls.add(functionCall);
            throw e.getTargetException();
        }
        functionCall.returnValue = returnValue;
        functionCalls.add(functionCall);
        return returnValue;
    }
}
