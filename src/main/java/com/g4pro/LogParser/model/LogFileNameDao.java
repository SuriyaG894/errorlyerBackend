package com.g4pro.LogParser.model;

public class LogFileNameDao {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LogFileNameDao(String fileName) {
        this.fileName = fileName;
    }
}
