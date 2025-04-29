package com.g4pro.LogParser.service;

import com.g4pro.LogParser.entity.Answer;
import com.g4pro.LogParser.entity.Reply;
import com.g4pro.LogParser.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepo;


    public Answer addAnswer(Answer answer){
        return answerRepo.save(answer);
    }

    public List<Answer> getAllAnswers(){
        List<Answer> answers = answerRepo.findAll();
        Collections.reverse(answers);
        return answers;
    }

    public Answer getAnswerById(long id){
        return answerRepo.findById(id).orElse(null);
    }

    public Answer mapReplyToAnswer(long id,List<Reply> replies){
        Answer a = answerRepo.findById(id).orElse(null);
        if(a!=null){
            a.setReplies(replies);
            return answerRepo.save(a);
        }
        return null;
    }


    public Answer mapLikesToAnswer(long id,int likes){
        Answer a = answerRepo.findById(id).orElse(null);
        if(a!=null){
            a.setLikes(a.getLikes()+1);
            return answerRepo.save(a);
        }
        return null;
    }

    public Answer updateAnswer(long id,Answer answer){
        Answer a = answerRepo.findById(id).orElse(null);
        if(a!=null){
            a.setText(answer.getText());
            a.setLikes(answer.getLikes());
            a.setReplies(answer.getReplies());
            return answerRepo.save(a);
        }
        return null;
    }

    public Answer deleteAnswer(long id){
        Answer a = answerRepo.findById(id).orElse(null);
        if(a!=null){
            answerRepo.deleteById(id);
            return a;
        }
        return null;
    }

}
