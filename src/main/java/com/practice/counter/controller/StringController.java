package com.practice.counter.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class StringController {

    private static String FILE_NAME = "lorem.txt";

    @RequestMapping("/char/{number}")
    public String getSubstring(@PathVariable("number") int number) {
        String content = "";
        try {
            Path mainPath = Paths.get(ClassLoader.getSystemResource(FILE_NAME).toURI());
            content = new String(Files.readAllBytes(Paths.get(mainPath.toString())));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return content.substring(number, number*2);
    }

    @RequestMapping("/getList")
    public List<String> getOrderedText() {
        List<String> list = new ArrayList<>();
        try (Stream<String> fileStream = Files.lines(Paths.get(ClassLoader.getSystemResource(FILE_NAME).toURI()))) {

            list = fileStream.sorted(Comparator.naturalOrder()).filter(line -> !line.isEmpty()).collect(Collectors.toList());

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return list;
    }
}