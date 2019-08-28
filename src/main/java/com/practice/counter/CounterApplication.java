package com.practice.counter;

import com.practice.counter.dinamicproxy.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Proxy;

@SpringBootApplication
public class CounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CounterApplication.class, args);

        ICar ICar = new ICarImpl();
        ClassLoader cl = ICar.class.getClassLoader();
        ICar car = (ICar) Proxy.newProxyInstance(cl,
                new Class[] {ICar.class}, new GenericLogger(ICar));
        car.start();
        car.forward();
        car.backward();
        car.stop();

        IVehicle IVehicleStub = CustomMock.createMock(new IVehicleImpl());
    }
}
