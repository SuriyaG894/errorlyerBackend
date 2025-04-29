package com.g4pro.LogParser.controller;

import com.g4pro.LogParser.entity.Reply;
import com.g4pro.LogParser.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping("/addReply")
    public ResponseEntity<Reply> addReply(@RequestBody Reply reply) {
        Reply r = replyService.addReply(reply);
        if (r != null) {
            return new ResponseEntity<>(r, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getAllReplies")
    public ResponseEntity<List<Reply>> getAllReplies() {
        List<Reply> list = replyService.getAllReplies();
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getReplyById/{id}")
    public ResponseEntity<Reply> getReplyById(@PathVariable("id") long id) {
        Reply r = replyService.getById(id);
        if (r != null) {
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateReply/{id}")
    public ResponseEntity<Reply> updateReply(@PathVariable("id") long id, @RequestBody Reply reply) {
        Reply r = replyService.updateReply(id, reply);
        if (r != null) {
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteReply/{id}")
    public ResponseEntity<Reply> deleteReply(@PathVariable("id") long id) {
        Reply r = replyService.deleteReply(id);
        if (r != null) {
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/addLikesToReply/{id}")
    public ResponseEntity<Reply> addLikesToReply(@PathVariable("id") long id, @RequestBody int like) {
        Reply r = replyService.addLikesToQuestion(id, like);
        if (r != null) {
            return new ResponseEntity<>(r, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
