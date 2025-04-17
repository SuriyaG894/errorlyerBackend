package com.g4pro.LogParser.repository;

import com.g4pro.LogParser.entity.StackTraceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StackTraceEntryRepository extends JpaRepository<StackTraceEntry,Long> {
}
