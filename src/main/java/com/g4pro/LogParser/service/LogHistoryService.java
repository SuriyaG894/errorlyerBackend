package com.g4pro.LogParser.service;

import com.g4pro.LogParser.entity.LogHistory;
import com.g4pro.LogParser.repository.LogHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LogHistoryService {

    @Autowired
    public LogHistoryRepo logHistoryRepo;

    public ResponseEntity<?> saveLogHistory(LogHistory logHistory){
        System.out.println(logHistory.getDate() + logHistory.getFileName());
        logHistoryRepo.save(logHistory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> findByUsername(String username) {
        return new ResponseEntity<>(logHistoryRepo.findFileNameByUsername(username),HttpStatus.OK);
    }

    public ResponseEntity<?> getCountByUsername(String username) {
        return new ResponseEntity<>(logHistoryRepo.getCountByUsername(username),HttpStatus.OK);
    }
}
