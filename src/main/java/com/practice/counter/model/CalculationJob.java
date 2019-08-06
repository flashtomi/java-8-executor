package com.practice.counter.model;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class CalculationJob  implements Callable<String> {
    private int input;

    public CalculationJob(int input) {
        this.input = input;
    }

    @Override
    public String call() {
        String url = String.format("http://localhost:8080/char/%d", input);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, String.class);
    }
}
