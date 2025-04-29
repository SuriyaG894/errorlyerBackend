package com.g4pro.LogParser.repository;

import com.g4pro.LogParser.entity.LogHistory;
import com.g4pro.LogParser.model.LogFileCountDao;
import com.g4pro.LogParser.model.LogFileNameDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogHistoryRepo extends JpaRepository<LogHistory,Long> {


    @Query(value = "select file_name from log_history where username=?1 order by id desc limit 1 ",nativeQuery = true)
    String findFileNameByUsername(String username);

    @Query(value = "select count(*) as log_file_count from log_history where username=?1",nativeQuery = true)
    Long getCountByUsername(String username);

}
