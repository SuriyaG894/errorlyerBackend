package com.g4pro.LogParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.g4pro.LogParser.model.ErrorDTO;
import com.g4pro.LogParser.repository.ErrorRepo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
@SpringBootTest
public class ErrorController {
     @Autowired
    private ErrorRepo errorRepo;
    @Test
    @DisplayName("Test Case: Add Student through CSV")
    public void testPostStudentUsingCSV() throws IOException, CsvException {
        String filePath = "src/main/resources/ExceptionDetails1.csv";
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

            // errorList.add(errorDTO);
            errorRepo.save(errorDTO);
        }

    }

}
