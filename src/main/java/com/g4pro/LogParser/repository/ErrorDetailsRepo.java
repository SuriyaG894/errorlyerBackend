package com.g4pro.LogParser.repository;

import com.g4pro.LogParser.model.ConsoleErrorCountDAO;
import com.g4pro.LogParser.model.ErrorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "select exception_type,count(*) as exception_count from error_details where username=?1 GROUP BY exception_type",nativeQuery = true)
    List<ConsoleErrorCountDAO> getCountByUsername(String username);

    @Query(value = "SELECT * FROM error_details WHERE username=?1",nativeQuery = true)
    List<ErrorDetails> getUserSpecificDetails(String username);

    @Query(value="select * from error_details where exception_type=?1 and username=?2",nativeQuery = true)
    List<ErrorDetails> findByExceptionTypeAndUsername(String exceptionType,String username);
}

