package com.g4pro.LogParser.service;

import com.g4pro.LogParser.entity.ErrorHistory;
import com.g4pro.LogParser.entity.ErrorHistoryStackTrace;
import com.g4pro.LogParser.model.CombinedLogHistoryDTO;
import com.g4pro.LogParser.model.CombinedLogHistoryStackTraceDTO;
import com.g4pro.LogParser.model.ErrorCountDTO;
import com.g4pro.LogParser.repository.ErrorHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CombinedHistoryService {

    @Autowired
    private ErrorHistoryRepository errorHistoryRepository;

//    public ResponseEntity<?> saveCombinedLogHistory(List<CombinedLogHistoryDTO> combinedLogHistoryDTO) {
//        List<ErrorHistory> errorHistories = new ArrayList<>();
//// String timestamp, String level, String thread, String exceptionName, String errorMessage, List<ErrorHistoryStackTrace> errorHistoryStackTraces,String username
//        for(CombinedLogHistoryDTO dto:combinedLogHistoryDTO){
//            List<ErrorHistoryStackTrace> errorHistoryStackTraces = new ArrayList<>();
//
//            ErrorHistory errorHistory =  new ErrorHistory(dto.getTimestamp(),dto.getLevel(),dto.getThread(),dto.getExceptionName(),dto.getErrorMessage(),dto.getCombinedLogHistoryStackTraceDTOS(),dto.getUsername()));
//            for(CombinedLogHistoryStackTraceDTO cdto:dto.getCombinedLogHistoryStackTraceDTOS()){
//                errorHistoryStackTraces.add(new ErrorHistoryStackTrace(cdto.getClassName(),cdto.getMethodName(),cdto.getFileName(),cdto.getLineNumber(),null));
//            }
//        }
//
//        return new ResponseEntity<>("All log history Saved", HttpStatus.OK);
//
//    }
public ResponseEntity<?> saveCombinedLogHistory(List<CombinedLogHistoryDTO> dtos) {
    List<ErrorHistory> histories = dtos.stream()
            .map(this::convertDtoToEntity)
            .collect(Collectors.toList());

    return new ResponseEntity<>( errorHistoryRepository.saveAll(histories),HttpStatus.OK);
}

    private ErrorHistory convertDtoToEntity(CombinedLogHistoryDTO dto) {
        ErrorHistory errorHistory = new ErrorHistory();
        errorHistory.setTimestamp(dto.getTimestamp());
        errorHistory.setLevel(dto.getLevel());
        errorHistory.setThread(dto.getThread());
        errorHistory.setExceptionName(dto.getExceptionName());
        errorHistory.setErrorMessage(dto.getErrorMessage());
        errorHistory.setUsername(dto.getUsername());

        List<ErrorHistoryStackTrace> stackTraces = dto.getStackTrace().stream()
                .map(stackDTO -> {
                    ErrorHistoryStackTrace stackTrace = new ErrorHistoryStackTrace();
                    stackTrace.setClassName(stackDTO.getClassName());
                    stackTrace.setMethodName(stackDTO.getMethodName());
                    stackTrace.setFileName(stackDTO.getFileName());
                    stackTrace.setLineNumber(stackDTO.getLineNumber());
                    stackTrace.setErrorHistory(errorHistory);
                    return stackTrace;
                })
                .collect(Collectors.toList());

        errorHistory.setStackTrace(stackTraces);
        return errorHistory;
    }

    public ResponseEntity<?> findByUsername(String username) {
        List<ErrorHistory> errorHistory = errorHistoryRepository.findByUsername(username);
//        if(errorHistory.isEmpty()){
//            return new ResponseEntity<>("History Not Found",HttpStatus.BAD_REQUEST);
//        }
        return new ResponseEntity<>(errorHistory,HttpStatus.OK);
    }

    public ResponseEntity<?> getLogExceptionCount(String username) {
        List<ErrorCountDTO> errorCountDTOS = errorHistoryRepository.getExceptionCountByUsername(username);

        return new ResponseEntity<>(errorCountDTOS,HttpStatus.OK);
    }

    public ResponseEntity<?> getLogHistoryExceptionDetail(String exceptionName, String username) {
        return new ResponseEntity<>(errorHistoryRepository.findByExceptionNameAndUsername(exceptionName,username),HttpStatus.OK);
    }
}
