package com.g4pro.LogParser.model;

public class ErrorCountDTO {
    private Long count;
    private String exceptionName;

    public ErrorCountDTO(String exceptionName, Long count) {
        this.count = count;
        this.exceptionName = exceptionName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }
}
