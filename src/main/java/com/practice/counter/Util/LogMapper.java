package com.practice.counter.Util;

import com.practice.counter.model.LogModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LogMapper {
    private final static String levelRgx = "(?<level>INFO|ERROR|WARN)";
    private final static String timestampRgx = "\\[(?<timestamp>[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9])]";
    private final static String messageRgx = "(?<message>(Message=)(.*)(?= ))";
    private final static String requestIdRgx = "(?<requestId>(RequestId=)(.*))";
    private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    private static Pattern PatternFullLog = Pattern.compile(timestampRgx + " " + levelRgx + " " + messageRgx + " " + requestIdRgx, Pattern.DOTALL);

    public List<LogModel> mapLogFileToModel(String fileName) {
        List<String> list;
        Matcher matcher;
        List<LogModel> logList = new ArrayList<>();

        try (Stream<String> fileStream = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))) {

            list = fileStream.collect(Collectors.toList());
            for (String line : list) {
                matcher = PatternFullLog.matcher(line);

                while(matcher.find()) {
                    logList.add(buildLogModel(matcher));
                }
            }
        } catch (IOException | URISyntaxException | ParseException e) {
            e.printStackTrace();
        }

        return logList;
    }

    private LogModel buildLogModel (Matcher matcher) throws ParseException {
        Date timestamp = formatter.parse(matcher.group("timestamp"));
        String type = matcher.group("level");
        String message = matcher.group("message");
        String requestId = matcher.group("requestId");
        return new LogModel(timestamp, type, message, requestId);
    }

}
