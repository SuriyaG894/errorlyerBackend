package com.g4pro.LogParser.controller;

import com.g4pro.LogParser.entity.LogHistory;
import com.g4pro.LogParser.entity.ParsedError;
import com.g4pro.LogParser.entity.StackTraceEntry;
import com.g4pro.LogParser.model.CombinedLogHistoryDTO;
import com.g4pro.LogParser.repository.LogHistoryRepo;
import com.g4pro.LogParser.repository.ParsedErrorRepository;
import com.g4pro.LogParser.service.CombinedHistoryService;
import com.g4pro.LogParser.service.LogHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private ParsedErrorRepository parsedErrorRepository;

    @Autowired
    private LogHistoryService logHistoryService;

    @Autowired
    private CombinedHistoryService combinedHistoryService;



    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,@RequestParam("username") String username) throws IOException {
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
                    currentError.setUsername(username);
                    parsedErrorRepository.save(currentError);
                }

                currentError = new ParsedError();
                currentError.setTimestamp(headerMatcher.group(1));
                currentError.setLevel(headerMatcher.group(2));
                currentError.setThread(headerMatcher.group(3));
                currentError.setErrorMessage(headerMatcher.group(4));
            } else if (currentError != null) {
                Matcher exceptionMatcher = exceptionLine.matcher(line);
                if (exceptionMatcher.find()) {
                    currentError.setExceptionName(!exceptionMatcher.group(1).isEmpty() ?exceptionMatcher.group(1).split("\\.")[2]:"");
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
            currentError.setUsername(username);
            parsedErrorRepository.save(currentError);
        }

        return ResponseEntity.ok("Log parsed and saved.");
    }


    @GetMapping
    public List<ParsedError> getAll() {
        return parsedErrorRepository.findAll();
    }

    @GetMapping("/home")
    public String printHome() {
        return "Home";
    }

    @PostMapping("/logHistory")
    public ResponseEntity<?> saveLogHistory(@RequestBody LogHistory logHistory){
        return logHistoryService.saveLogHistory(logHistory);
    }

    @DeleteMapping("/deleteLog")
    public void deleteLog(){
        parsedErrorRepository.deleteAll();
    }

    @PostMapping("/saveCombinedLogHistory")
    public ResponseEntity<?> saveCombinedLogHistory(@RequestBody List<CombinedLogHistoryDTO> combinedLogHistoryDTO){
//        System.out.println(combinedLogHistoryDTO.getFirst().getStackTrace().toString());
        return combinedHistoryService.saveCombinedLogHistory(combinedLogHistoryDTO);
    }

    @GetMapping("/getLogHistory/{username}")
    public ResponseEntity<?> getLogHistory(@PathVariable("username") String username){
        return combinedHistoryService.findByUsername(username);
    }

    @GetMapping("/getLogExceptionCount/{username}")
    public ResponseEntity<?> getLogExceptionCount(@PathVariable("username") String username){
        return combinedHistoryService.getLogExceptionCount(username);
    }

    @GetMapping("/getLogHistoryExceptionDetail/{exceptionName}/{username}")
    public ResponseEntity<?> getLogHistoryExceptionDetail(@PathVariable("exceptionName") String exceptionName,@PathVariable("username") String username){
        return combinedHistoryService.getLogHistoryExceptionDetail(exceptionName,username);
    }

    @GetMapping("/getLogFileName/{username}")
    public ResponseEntity<?> getLogFileName(@PathVariable("username") String username){
        return logHistoryService.findByUsername(username);
    }

    @GetMapping("/getLogFileCount/{username}")
    public ResponseEntity<?> getLogFileCount(@PathVariable("username") String username){
        return logHistoryService.getCountByUsername(username);
    }

//    @GetMapping("/getCount")
//    public void getCount(){
//        return logHistoryService.getLogCount();
//    }
}
