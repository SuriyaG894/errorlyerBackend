package com.g4pro.LogParser.repository;

import com.g4pro.LogParser.entity.ErrorHistoryStackTrace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorHistoryStackTraceRepositroy extends JpaRepository<ErrorHistoryStackTrace,Long> {
}
