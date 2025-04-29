package com.g4pro.LogParser.controller;

import com.g4pro.LogParser.model.ErrorDTO;
import com.g4pro.LogParser.model.ErrorDetailsDTO;
import com.g4pro.LogParser.service.ErrorDetailsService;
import com.g4pro.LogParser.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ErrorDetailsController {

    @Autowired
    private ErrorDetailsService errorDetailsService;

    @Autowired
    private ErrorService errorService;

    @PostMapping("/saveErrorDetails")
    public ResponseEntity<ErrorDetailsDTO> saveErrorDetails(@RequestBody ErrorDetailsDTO errorDetailsDTO) {
        ErrorDetailsDTO savedError = errorDetailsService.saveErrorDetails(errorDetailsDTO);
        return new ResponseEntity<>(savedError, HttpStatus.CREATED);
    }

    @GetMapping("/getAllErrorDetails/{username}")
    public ResponseEntity<List<ErrorDetailsDTO>> getAllErrorDetails(@PathVariable("username") String username) {
        List<ErrorDetailsDTO>errorDetailsList = errorDetailsService.getAllErrorDetails(username);
        return new ResponseEntity<>(errorDetailsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ErrorDetailsDTO> getErrorDetailsById(@PathVariable Long id) {
        return errorDetailsService.getErrorDetailsById(id)
                .map(errorDetails -> new ResponseEntity<>(errorDetails, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteErrorDetails(@PathVariable Long id) {
        errorDetailsService.deleteErrorDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // @GetMapping("/search")
    // public ResponseEntity<List<ErrorDetailsDTO>> searchErrorDetails(@RequestParam String term) {
    //     List<ErrorDetailsDTO> errorDetailsList = errorDetailsService.searchErrorDetails(term);
    //     return new ResponseEntity<>(errorDetailsList, HttpStatus.OK);
    // }

    @GetMapping("/search")
    public ResponseEntity<List<ErrorDetailsDTO>>searchErrorType(@RequestParam String type){
        List<ErrorDetailsDTO> e = errorDetailsService.searchError(type);
        if(e!=null){
            return new ResponseEntity<>(e,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addNewError")
    public ResponseEntity<?>addNewError(@RequestBody ErrorDTO errorDTO){

        ErrorDTO e = errorService.saveNewError(errorDTO);
        if(e!=null){
            return new ResponseEntity<>(e,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping("/getConsoleErrorCount/{username}")
    public ResponseEntity<?> getConsoleErrorCount(@PathVariable("username") String username){
        return errorDetailsService.getConsoleErrorCount(username);
    }

    @GetMapping("/getConsoleErrorByException/{exceptionType}/{username}")
    public ResponseEntity<?> findByExceptionTypeAndUsername(@PathVariable("exceptionType") String exceptionType,@PathVariable("username") String username){
        return errorDetailsService.findByExceptionTypeAndUsername(exceptionType,username);
    }

}
