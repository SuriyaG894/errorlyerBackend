package com.g4pro.LogParser.service;

import com.g4pro.LogParser.model.ErrorDetails;
import com.g4pro.LogParser.model.ErrorDetailsDTO;
import com.g4pro.LogParser.repository.ErrorDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ErrorDetailsService {

    @Autowired
    private ErrorDetailsRepo errorDetailsRepo;

     public ErrorDetailsDTO saveErrorDetails(ErrorDetailsDTO errorDetailsDTO) {
        ErrorDetails errorDetails = convertToEntity(errorDetailsDTO);
        ErrorDetails savedErrorDetails = errorDetailsRepo.save(errorDetails);
        return convertToDTO(savedErrorDetails);
    }

    public List<ErrorDetailsDTO> getAllErrorDetails(String username) {
        return errorDetailsRepo.getUserSpecificDetails(username).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ErrorDetailsDTO> getErrorDetailsById(Long id) {
        return errorDetailsRepo.findById(id)
                .map(this::convertToDTO);
    }

    public void deleteErrorDetails(Long id) {
        errorDetailsRepo.deleteById(id);
    }

    // public List<ErrorDetailsDTO> searchErrorDetails(String searchTerm) {
    //     return errorDetailsRepo.searchByTerm(searchTerm).stream()
    //             .map(this::convertToDTO)
    //             .collect(Collectors.toList());
    // }

    // Helper methods for conversion between Entity and DTO
    private ErrorDetails convertToEntity(ErrorDetailsDTO dto) {
        ErrorDetails entity = new ErrorDetails();
        entity.setId(dto.getId());
        entity.setExceptionType(dto.getExceptionType());
        entity.setErrorType(dto.getErrorType());
        entity.setErrorMessage(dto.getErrorMessage());
        entity.setLineNumber(dto.getLineNumber());
        entity.setFileName(dto.getFileName());
        entity.setStackTrace(dto.getStackTrace());
        entity.setRelevantCode(dto.getRelevantCode());
        entity.setUsername((dto.getUsername()));
        return entity;
    }

    private ErrorDetailsDTO convertToDTO(ErrorDetails entity) {
        ErrorDetailsDTO dto = new ErrorDetailsDTO();
        dto.setId(entity.getId());
        dto.setExceptionType(entity.getExceptionType());
        dto.setErrorType(entity.getErrorType());
        dto.setErrorMessage(entity.getErrorMessage());
        dto.setLineNumber(entity.getLineNumber());
        dto.setFileName(entity.getFileName());
        dto.setStackTrace(entity.getStackTrace());
        dto.setRelevantCode(entity.getRelevantCode());
        dto.setTimestamp(entity.getTimestamp());
        dto.setUsername(entity.getUsername());
        return dto;
    }

    public List<ErrorDetailsDTO> searchError(String eType) {
        List<ErrorDetails> errorDetailsList = errorDetailsRepo.findByExceptionTypeContaining(eType);
        return errorDetailsList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> getConsoleErrorCount(String username) {
        return new ResponseEntity<>(errorDetailsRepo.getCountByUsername(username), HttpStatus.OK);
    }


    public ResponseEntity<?> findByExceptionTypeAndUsername(String exceptionType, String username) {
         return new ResponseEntity<>(errorDetailsRepo.findByExceptionTypeAndUsername(exceptionType,username),HttpStatus.OK);
    }
}
