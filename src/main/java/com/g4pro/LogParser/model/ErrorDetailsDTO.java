package com.g4pro.LogParser.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorDetailsDTO {
     private Long id;
    private String exceptionType;
    private String errorType;
    private String errorMessage;
    private Integer lineNumber;
    private String fileName;
    private List<String> stackTrace = new ArrayList<>();
    private String relevantCode;
    private LocalDateTime timestamp;
    private String Errortype = "Console";

    public String getErrortype() {
        return Errortype;
    }

    public void setErrortype(String errortype) {
        Errortype = errortype;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(List<String> stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getRelevantCode() {
        return relevantCode;
    }

    public void setRelevantCode(String relevantCode) {
        this.relevantCode = relevantCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
