package com.g4pro.LogParser.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;
    private String cause;
    private String howToFix;
    private String beforeExample;
    private String afterExample;
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getHowToFix() {
        return howToFix;
    }

    public void setHowToFix(String howToFix) {
        this.howToFix = howToFix;
    }

    public String getBeforeExample() {
        return beforeExample;
    }

    public void setBeforeExample(String beforeExample) {
        this.beforeExample = beforeExample;
    }

    public String getAfterExample() {
        return afterExample;
    }

    public void setAfterExample(String afterExample) {
        this.afterExample = afterExample;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
