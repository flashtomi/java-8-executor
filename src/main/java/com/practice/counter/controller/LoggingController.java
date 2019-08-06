package com.practice.counter.controller;

import com.practice.counter.Util.LogMapper;
import com.practice.counter.model.LogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LoggingController {

    private final LogMapper logMapper;

    @Autowired
    public LoggingController(LogMapper logMapper) {
        this.logMapper = logMapper;
    }


    @RequestMapping("/filterLog")
    public Map<String, Long> getFilteredLog() throws ParseException {
        String fileName = "practice.log";
        List<LogModel> logList = logMapper.mapLogFileToModel(fileName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date start = sdf.parse("2019-07-27 18:46:00");
        Date end = sdf.parse("2019-07-27 20:10:40");

        return logList.stream().filter(logModel -> logModel.getType().equals("ERROR"))
                .filter(log -> (log.getDate()).after(start) && (log.getDate()).before(end))
                .collect(Collectors
                        .groupingBy(LogModel::getRequestId, Collectors.counting()));
    }
}
