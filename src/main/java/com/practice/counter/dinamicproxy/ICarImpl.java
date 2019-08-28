package com.practice.counter.dinamicproxy;

public class ICarImpl implements ICar {

    @Override
    public void start() {
        System.out.println("Car's engine started");
    }

    @Override
    public void stop() {
        System.out.println("Car's engine stopped");
    }

    @Override
    public void forward() {
        System.out.println("Car going forward");
    }

    @Override
    public void backward() {
        System.out.println("Car going backward");
    }
}
