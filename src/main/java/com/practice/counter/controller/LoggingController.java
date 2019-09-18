package com.practice.counter.controller;

import com.practice.counter.Util.LogHandler;
import com.practice.counter.model.LogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LoggingController {

    private final LogHandler logHandler;

    @Autowired
    public LoggingController(LogHandler logHandler) {
        this.logHandler = logHandler;
    }

    @RequestMapping("/filterLog")
    public Map<String, Long> getFilteredLog() throws ParseException {
        String fileName = "practice.log";
        List<LogModel> logList = logHandler.mapLogFileToModel(fileName);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");
        LocalDateTime start = LocalDateTime.parse("2019-07-27 18:46:00", formatter);
        LocalDateTime end = LocalDateTime.parse("2019-07-27 20:10:40", formatter);

        return logList.stream().filter(logModel -> logModel.getType().equals("ERROR"))
                .filter(log -> (log.getDate()).isAfter(start) && (log.getDate()).isBefore(end))
                .collect(Collectors
                        .groupingBy(LogModel::getRequestId, Collectors.counting()));
    }
}
