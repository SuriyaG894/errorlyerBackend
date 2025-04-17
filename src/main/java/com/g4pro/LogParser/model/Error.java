package com.g4pro.LogParser.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Error {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String errorText;

}
