package com.g4pro.LogParser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table()
public class ErrorHistoryStackTrace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;
    private String methodName;
    private String fileName;
    private int lineNumber;

    @ManyToOne
    @JoinColumn(name="error_history_id")
    @JsonIgnore
    private ErrorHistory errorHistory;

    public ErrorHistoryStackTrace() {
    }

    public ErrorHistoryStackTrace(String className, String methodName, String fileName, int lineNumber, ErrorHistory errorHistory) {
        this.className = className;
        this.methodName = methodName;
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.errorHistory = errorHistory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public ErrorHistory getErrorHistory() {
        return errorHistory;
    }

    public void setErrorHistory(ErrorHistory errorHistory) {
        this.errorHistory = errorHistory;
    }
}
