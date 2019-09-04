package com.practice.counter;

import com.practice.counter.dinamicproxy.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Proxy;

@SpringBootApplication
public class CounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CounterApplication.class, args);

        IVehicle vehicle = new Vehicle();
        IEngine engine = new Engine();
        ClassLoader cl = ICar.class.getClassLoader();
        Class<?>[] interfaces = ICar.class.getInterfaces();
        ICar car = (ICar) Proxy.newProxyInstance(cl, interfaces , new CarHandler(vehicle, engine));
        car.start();
        car.forward();
        car.backward();
        car.stop();
    }
}
