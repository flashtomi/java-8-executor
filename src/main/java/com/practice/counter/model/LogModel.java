package com.practice.counter.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogModel implements Serializable {
    private LocalDateTime date;
    private String type;
    private String message;
    private String requestId;
    private final static String levelRgx = "(?<level>INFO|ERROR|WARN)";
    private final static String timestampRgx = "\\[(?<timestamp>[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9])]";
    private final static String messageRgx = "(?<message>(Message=)(.*)(?= ))";
    private final static String requestIdRgx = "(?<requestId>(RequestId=)(.*))";
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");
    private final static Pattern patternFullLog = Pattern.compile(timestampRgx + " " + levelRgx + " " + messageRgx + " " + requestIdRgx, Pattern.DOTALL);

    public LogModel(LocalDateTime date, String type, String message, String requestId) {
        this.date = date;
        this.type = type;
        this.message = message;
        this.requestId = requestId;
    }

    public static LogModel buildLogModel (Matcher matcher) {
        LocalDateTime timestamp = LocalDateTime.parse(matcher.group("timestamp"), formatter);
        String type = matcher.group("level");
        String message = matcher.group("message");
        String requestId = matcher.group("requestId");
        return new LogModel(timestamp, type, message, requestId);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public static DateTimeFormatter getFormatter() {
        return formatter;
    }

    public static Pattern getPatternFullLog() {
        return patternFullLog;
    }

}
