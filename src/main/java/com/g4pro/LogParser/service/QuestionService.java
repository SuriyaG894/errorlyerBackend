package com.g4pro.LogParser.service;

import com.g4pro.LogParser.entity.Answer;
import com.g4pro.LogParser.entity.Question;
import com.g4pro.LogParser.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepo;

    public Question addQuestion(Question question){
        question.setLikes(0);
        question.setAnswers(null);
        return questionRepo.save(question);
    }

    public List<Question> getAllQuestions(){
        List<Question> list = questionRepo.findAll();
        Collections.reverse(list);
        return list;
    }

    public Question getQuestionById(long id){

        return questionRepo.findById(id).orElse(null);
    }

    public Question addAnswerToQuestion(long id, Answer answer) {
        Question question = questionRepo.findById(id).orElse(null);

        if (question != null) {
            answer.setQuestion(question);
            question.getAnswers().add(answer);
            questionRepo.save(question);

            return question;
        }
        return null;
    }

    public Question addLikesToQuestion(long id, int like) {
        Question question = questionRepo.findById(id).orElse(null);

        if (question != null) {
            question.setLikes(question.getLikes() + 1);
            questionRepo.save(question);
            return question;
        }
        return null;
    }

    public Question updateQuestion(long id, Question question){
        Question q = questionRepo.findById(id).orElse(null);

        if(q!=null){
            q.setText(question.getText());
            q.setAnswers(question.getAnswers());
            q.setLikes(question.getLikes());
            return questionRepo.save(q);
        }
        return null;

    }

    public Question deleteQuestion(long id){
        Question q = questionRepo.findById(id).orElse(null);
        if(q!=null){
            questionRepo.deleteById(id);
            return q;
        }
        return null;
    }
}
