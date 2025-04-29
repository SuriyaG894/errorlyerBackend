package com.g4pro.LogParser.controller;

import com.g4pro.LogParser.entity.Answer;
import com.g4pro.LogParser.entity.Question;
import com.g4pro.LogParser.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question q = questionService.addQuestion(question);
        if (q != null) {
            return new ResponseEntity<>(q, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> list = questionService.getAllQuestions();
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getQuestionById/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") long id) {
        Question q = questionService.getQuestionById(id);
        if (q != null) {
            return new ResponseEntity<>(q, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/mapAnswerToQuestion/{id}")
    public ResponseEntity<Question> mapAnswerToQuestion(@PathVariable("id") long id, @RequestBody Answer answer) {
        Question updatedQuestion = questionService.addAnswerToQuestion(id, answer);
        if (updatedQuestion != null) {
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/mapLikesToQuestion/{id}")
    public ResponseEntity<Question> mapLikesToQuestion(@PathVariable("id") long id, @RequestBody int like) {
        Question updatedQuestion = questionService.addLikesToQuestion(id, like);
        if (updatedQuestion != null) {
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable("id") long id, @RequestBody Question question) {
        Question updatedQuestion = questionService.updateQuestion(id, question);
        if (updatedQuestion != null) {
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable("id") long id) {
        Question updatedQuestion = questionService.deleteQuestion(id);
        if (updatedQuestion != null) {
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
