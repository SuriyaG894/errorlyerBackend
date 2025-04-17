package com.g4pro.LogParser.repository;

import com.g4pro.LogParser.model.ErrorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorDetailsRepo extends JpaRepository<ErrorDetails,Long> {

    //  @Query("SELECT e FROM ErrorDetails e WHERE " +
    //        "LOWER(e.exceptionType) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
    //        "LOWER(e.errorType) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
    //        "LOWER(e.errorMessage) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
    //        "LOWER(e.fileName) LIKE LOWER(CONCAT('%', :term, '%'))")
    // List<ErrorDetails> searchByTerm(@Param("term") String searchTerm);


    // @Query(value = "select * from errordto where title like % ?1 %", nativeQuery = true)
    //  ErrorDTO errorList(String eType);

    List<ErrorDetails> findByExceptionTypeContaining(String exceptionType);

    // @Query(value = "select e from ErrorDto e where e.title = :simpleType OR e.title = :fullType OR e.title LIKE %:simpleType%")
    //  ErrorDTO errorList(@Param("simpleType") String simpleType, @Param("fullType") String fullType);

}

