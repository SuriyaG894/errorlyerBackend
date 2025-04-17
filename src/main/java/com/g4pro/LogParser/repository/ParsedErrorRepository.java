package com.g4pro.LogParser.repository;

import com.g4pro.LogParser.entity.ParsedError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParsedErrorRepository extends JpaRepository<ParsedError,Long> {
}
