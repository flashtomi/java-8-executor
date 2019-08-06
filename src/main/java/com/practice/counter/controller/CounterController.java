package com.practice.counter.controller;

import com.practice.counter.model.CalculationJob;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class CounterController {
    private ExecutorService executorService;

    @RequestMapping("/")
    public List<Integer> index() {
        List<Integer> streamRange = IntStream.range(5, 100)
                    .boxed()
                    .collect(Collectors.toList());

        return streamRange.stream().filter(number -> number % 2 == 0).collect(Collectors.toList());
    }

    @RequestMapping("/{number}")
    public String counter(@PathVariable("number") int number) {
        String result = "";
        List<Future<String>> futures = getFutures(number);

        for (Future<String> future : futures) {
            try {
                result = result.concat(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

        return result;
    }

    private List<Future<String>> getFutures (int number) {
        List<Future<String>> futures = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(number);

        for (int i=1 ; i<=number; i++) {
            futures.add(executorService.submit(new CalculationJob(getRandomNumberInRange(i))));
        }
        return futures;
    }

    private static int getRandomNumberInRange(int max) {
        Random rand = new Random();
        return rand.ints(1, (max + 1)).findFirst().getAsInt();
    }
}
