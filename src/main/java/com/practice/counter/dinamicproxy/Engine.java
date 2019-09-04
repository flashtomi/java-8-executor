package com.practice.counter.dinamicproxy;

public class Engine implements IEngine {

    @Override
    public void start() {
        System.out.println("Engine started");
    }

    @Override
    public void stop() {
        System.out.println("Engine stopped");
    }
}
