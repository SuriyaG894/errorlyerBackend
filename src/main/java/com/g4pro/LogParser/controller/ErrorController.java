package com.g4pro.LogParser.controller;

import com.g4pro.LogParser.model.ErrorDTO;
import com.g4pro.LogParser.service.ErrorService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ErrorController {

    @Autowired
    private ErrorService errorService;


    @PostMapping("/addErrors")
    public ResponseEntity<?> addErrors(@RequestBody ErrorDTO errorDTO) throws IOException, CsvException {
    String filePath = "src/main/resources/data.csv"; // or take it as param later
    List<ErrorDTO> saved = errorService.addErrorsFromCSV(filePath);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
}

        @GetMapping("/getAllErrors")
        public ResponseEntity<List<ErrorDTO>> getAllErrors(){
            List<ErrorDTO> list = errorService.getAllErrors();

            if(!list.isEmpty()){
                return new ResponseEntity<>(list,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @GetMapping("getById/{id}")
        public ResponseEntity<ErrorDTO> getById(@PathVariable("id") long id){
            ErrorDTO e = errorService.getErrorById(id);
            if(e!=null){
                return new ResponseEntity<>(e,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
}


