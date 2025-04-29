package com.g4pro.LogParser.model;

import com.g4pro.LogParser.entity.ErrorHistoryStackTrace;

import java.util.ArrayList;
import java.util.List;

public class CombinedLogHistoryDTO {

    private String timestamp;
    private String level;
    private String thread;
    private String exceptionName;
    private String errorMessage;
    private String username;
    private List<CombinedLogHistoryStackTraceDTO> stackTrace = new ArrayList<>();

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CombinedLogHistoryStackTraceDTO> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<CombinedLogHistoryStackTraceDTO> stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Override
    public String toString() {
        return "CombinedLogHistoryDTO{" +
                "timestamp='" + timestamp + '\'' +
                ", level='" + level + '\'' +
                ", thread='" + thread + '\'' +
                ", exceptionName='" + exceptionName + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", username='" + username + '\'' +
                ", combinedLogHistoryStackTraceDTOS=" + stackTrace +
                '}';
    }
}
