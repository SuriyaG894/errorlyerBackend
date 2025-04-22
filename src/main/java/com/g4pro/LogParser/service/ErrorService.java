package com.g4pro.LogParser.service;

import com.g4pro.LogParser.model.ErrorDTO;
import com.g4pro.LogParser.repository.ErrorRepo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ErrorService {

    @Autowired
    private ErrorRepo errorRepo;

    public List<ErrorDTO> addErrorsFromCSV(String filePath) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> list = reader.readAll();
        reader.close();

        List<ErrorDTO> errorList = new ArrayList<>();

        for (int i = 1; i < list.size(); i++) {
            String[] row = list.get(i);

            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setTitle(row[0]);
            errorDTO.setCategory(row[1]);
            errorDTO.setCause(row[2]);
            errorDTO.setHowToFix(row[3]);
            errorDTO.setBeforeExample(row[4]);
            errorDTO.setAfterExample(row[5]);
            errorDTO.setNotes(row[6]);

            errorList.add(errorDTO);
        }

        return errorRepo.saveAll(errorList);
    }

    public List<ErrorDTO> getAllErrors() {
        return errorRepo.findAll();
    }

    public ErrorDTO addError(ErrorDTO errorDTO) {
        return errorRepo.save(errorDTO);
    }

    public ErrorDTO saveNewError(ErrorDTO e){
        return errorRepo.save(e);
    }

    
    public ErrorDTO getErrorById(long id){
        return errorRepo.findById(id).orElse(null);
    }

}