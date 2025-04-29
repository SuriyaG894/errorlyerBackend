package com.g4pro.LogParser.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ErrorHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timestamp;
    private String level;
    private String thread;
    private String exceptionName;
    private String errorMessage;
    private String username;
    private String errorSeachedThrough = "log";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany(mappedBy = "errorHistory", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ErrorHistoryStackTrace> stackTrace = new ArrayList<>();

    public ErrorHistory() {
    }

    public ErrorHistory(String timestamp, String level, String thread, String exceptionName, String errorMessage, List<ErrorHistoryStackTrace> stackTrace,String username) {
        this.timestamp = timestamp;
        this.level = level;
        this.thread = thread;
        this.exceptionName = exceptionName;
        this.errorMessage = errorMessage;
        this.stackTrace = stackTrace;
        this.username = username;
        this.errorSeachedThrough = "log";
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<ErrorHistoryStackTrace> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<ErrorHistoryStackTrace> stackTrace) {
        this.stackTrace = stackTrace;
    }
}
