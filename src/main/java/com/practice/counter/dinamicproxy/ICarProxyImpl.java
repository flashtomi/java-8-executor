package com.practice.counter.dinamicproxy;

public class ICarProxyImpl implements ICar {
    private IVehicle IVehicle;
    private IEngine IEngine;

    public ICarProxyImpl(IVehicle IVehicle, IEngine IEngine) {
        this.IVehicle = IVehicle;
        this.IEngine = IEngine;
    }

    @Override
    public void start() {
        System.out.println("ICarProxyImpl: Begin of start()");
        IEngine.start();
        System.out.println("ICarProxyImpl: End of start()");
    }

    @Override
    public void stop() {
        System.out.println("ICarProxyImpl: Begin of stop()");
        IEngine.stop();
        System.out.println("ICarProxyImpl: End of stop()");
    }

    @Override
    public void forward() {
        System.out.println("ICarProxyImpl: Begin of forward()");
        IVehicle.forward();
        System.out.println("ICarProxyImpl: End of forward()");
    }

    @Override
    public void backward() {
        System.out.println("ICarProxyImpl: Begin of backward()");
        IVehicle.backward();
        System.out.println("ICarProxyImpl: End of backward()");
    }
}
