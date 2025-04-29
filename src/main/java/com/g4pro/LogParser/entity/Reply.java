package com.g4pro.LogParser.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;
    private String text;
    private String username;
    private int likes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern = "dd MMMM yyyy h:mm a", timezone = "Asia/Kolkata")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    @JsonBackReference
    private Answer answer;
}
