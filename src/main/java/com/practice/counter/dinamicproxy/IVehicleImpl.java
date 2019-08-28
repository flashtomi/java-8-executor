package com.practice.counter.dinamicproxy;

public class IVehicleImpl implements IVehicle {

    @Override
    public void forward() {
        System.out.println("Vehicle going forward");
    }

    @Override
    public void backward() {
        System.out.println("Vehicle going backward");
    }
}
