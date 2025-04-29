package com.g4pro.LogParser.repository;

import com.g4pro.LogParser.entity.ErrorHistory;
import com.g4pro.LogParser.model.ErrorCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ErrorHistoryRepository extends JpaRepository<ErrorHistory,Long> {

    List<ErrorHistory> findByUsername(String username);

    @Query(value = "SELECT * FROM error_history WHERE exception_name = ?1 AND username = ?2", nativeQuery = true)
    List<ErrorHistory> findByExceptionNameAndUsername(String exceptionName, String username);


    @Query(value = "SELECT exception_name, COUNT(*) AS exception_order " +
            "FROM error_history " +
            "WHERE username = ?1 " +
            "AND exception_name IS NOT NULL " +
            "AND TRIM(exception_name) <> '' " +
            "GROUP BY exception_name " +
            "ORDER BY exception_order DESC",
            nativeQuery = true)
    List<ErrorCountDTO> getExceptionCountByUsername(String username);

}
