package com.g4pro.LogParser.controller;

import com.g4pro.LogParser.entity.ParsedError;
import com.g4pro.LogParser.entity.StackTraceEntry;
import com.g4pro.LogParser.repository.ParsedErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin("http://localhost:4200/")
public class LogController {

    @Autowired
    ParsedErrorRepository errorRepo;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        String line;
        ParsedError currentError = null;

        Pattern errorHeader = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}) \\[([^]]+)] \\[([^]]+)] (.*)");
        Pattern exceptionLine = Pattern.compile("^([\\w.$]+):\\s+(.*)");
        Pattern stackTraceLine = Pattern.compile("^\\s*at\\s+(\\S+)\\.(\\S+)\\(([^:()]+)(?::(\\d+))?\\)");

        while ((line = reader.readLine()) != null) {
            Matcher headerMatcher = errorHeader.matcher(line);
            if (headerMatcher.find()) {
                // Save the previous error (if exists)
                if (currentError != null) {
                    errorRepo.save(currentError);
                }

                currentError = new ParsedError();
                currentError.setTimestamp(headerMatcher.group(1));
                currentError.setLevel(headerMatcher.group(2));
                currentError.setThread(headerMatcher.group(3));
                currentError.setErrorMessage(headerMatcher.group(4));
            } else if (currentError != null) {
                Matcher exceptionMatcher = exceptionLine.matcher(line);
                if (exceptionMatcher.find()) {
                    currentError.setExceptionName(exceptionMatcher.group(1));
                    currentError.setErrorMessage(exceptionMatcher.group(2)); // override error message if better
                } else {
                    Matcher stackMatcher = stackTraceLine.matcher(line);
                    if (stackMatcher.find()) {
                        StackTraceEntry entry = new StackTraceEntry();
                        entry.setClassName(stackMatcher.group(1));
                        entry.setMethodName(stackMatcher.group(2));
                        entry.setFileName(stackMatcher.group(3));
                        if (stackMatcher.group(4) != null) {
                            entry.setLineNumber(Integer.parseInt(stackMatcher.group(4)));
                        }
                        entry.setParsedError(currentError);
                        currentError.getStackTrace().add(entry);
                    }
                }
            }
        }

        // Save the last error after EOF
        if (currentError != null) {
            errorRepo.save(currentError);
        }

        return ResponseEntity.ok("Log parsed and saved.");
    }


    @GetMapping
    public List<ParsedError> getAll() {
        return errorRepo.findAll();
    }

    @GetMapping("/home")
    public String printHome() {
        return "Home";
    }
}
