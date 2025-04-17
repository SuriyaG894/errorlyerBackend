package com.g4pro.LogParser.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class StackTraceEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String className;
    private String methodName;
    private String fileName;
    private int lineNumber;

    @ManyToOne
    @JoinColumn(name="parsed_error_id")
    @JsonIgnore
    private ParsedError parsedError;

    public StackTraceEntry() {
    }

    public StackTraceEntry(int lineNumber, String fileName, String methodName, String className) {
        this.lineNumber = lineNumber;
        this.fileName = fileName;
        this.methodName = methodName;
        this.className = className;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
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

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public ParsedError getParsedError() {
        return parsedError;
    }

    public void setParsedError(ParsedError parsedError) {
        this.parsedError = parsedError;
    }
}
