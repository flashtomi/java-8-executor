package com.practice.counter.model;

import java.util.Date;

public class LogModel {
    private Date date;
    private String type;
    private String message;
    private String requestId;

    public LogModel(Date date, String type, String message, String requestId) {
        this.date = date;
        this.type = type;
        this.message = message;
        this.requestId = requestId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}
