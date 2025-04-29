package com.g4pro.LogParser.controller;

import com.g4pro.LogParser.entity.Answer;
import com.g4pro.LogParser.entity.Reply;
import com.g4pro.LogParser.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/addAnswer")
    public ResponseEntity<Answer> addAnswer(@RequestBody Answer answer){
        Answer a = answerService.addAnswer(answer);
        if(a!=null){
            return new ResponseEntity<>(a, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getAllAnswers")
    public ResponseEntity<List<Answer>> getAllAnswers(){
        List<Answer> list = answerService.getAllAnswers();
        if(!list.isEmpty()){
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAnswerById/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable("id") long id){
        Answer a = answerService.getAnswerById(id);
        if(a!=null){
            return new ResponseEntity<>(a,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/mapReplyToAnswer/{id}")
    public ResponseEntity<Answer> mapReplyToAnswer(@PathVariable("id") long id, @RequestBody List<Reply> replies) {
        Answer updatedAnswer = answerService.mapReplyToAnswer(id, replies);
        if (updatedAnswer != null) {
            return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/mapLikesToAnswer/{id}")
    public ResponseEntity<Answer> mapLikesToAnswer(@PathVariable("id") long id, @RequestBody int likes) {
        Answer updatedAnswer = answerService.mapLikesToAnswer(id, likes);
        if (updatedAnswer != null) {
            return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateAnswer/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable("id") long id, @RequestBody Answer answer) {
        Answer updatedAnswer = answerService.updateAnswer(id, answer);
        if (updatedAnswer != null) {
            return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteAnswer/{id}")
    public ResponseEntity<Answer> deleteAnswer(@PathVariable("id") long id) {
        Answer deletedAnswer = answerService.deleteAnswer(id);
        if (deletedAnswer != null) {
            return new ResponseEntity<>(deletedAnswer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
