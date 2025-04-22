package com.g4pro.LogParser.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ParsedError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timestamp;
    private String level;
    private String thread;
    private String exceptionName;
    private String errorMessage;
    @OneToMany(mappedBy = "parsedError", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<StackTraceEntry> stackTrace = new ArrayList<>();

    public ParsedError() {
    }

    public ParsedError(String timestamp, String level, String thread, String exceptionName, String errorMessage) {
        this.timestamp = timestamp;
        this.level = level;
        this.thread = thread;
        this.exceptionName = exceptionName;
        this.errorMessage = errorMessage;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ParsedError{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
                ", level='" + level + '\'' +
                ", thread='" + thread + '\'' +
                ", exceptionName='" + exceptionName + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", stackTrace=" + stackTrace +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StackTraceEntry> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<StackTraceEntry> stackTrace) {
        this.stackTrace = stackTrace;
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
}
