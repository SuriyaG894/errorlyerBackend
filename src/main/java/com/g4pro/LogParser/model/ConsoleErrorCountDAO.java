package com.g4pro.LogParser.model;

public class ConsoleErrorCountDAO {

    private String exceptionName;
    private Long count;

    public ConsoleErrorCountDAO(String exceptionName, Long count) {
        this.exceptionName = exceptionName;
        this.count = count;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
