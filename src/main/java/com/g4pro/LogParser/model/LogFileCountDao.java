package com.g4pro.LogParser.model;

public class LogFileCountDao {
    private Long fileCount;

    public LogFileCountDao(Long fileCount) {
        this.fileCount = fileCount;
    }

    public Long getFileCount() {
        return fileCount;
    }

    public void setFileCount(Long fileCount) {
        this.fileCount = fileCount;
    }
}
