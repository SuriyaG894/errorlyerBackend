package com.g4pro.LogParser.repository;

import com.g4pro.LogParser.model.ErrorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepo extends JpaRepository<ErrorDTO,Long> {

}
