package com.g4pro.LogParser.service;

import com.g4pro.LogParser.entity.Reply;
import com.g4pro.LogParser.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepo;

    public Reply addReply(Reply reply){
        return replyRepo.save(reply);
    }

    public List<Reply> getAllReplies(){
        List<Reply> replies = replyRepo.findAll();
        Collections.reverse(replies);
        return replies;
    }

    public Reply getById(long id){
        return replyRepo.findById(id).orElse(null);
    }

    public Reply updateReply(long id,Reply reply){
        Reply r = replyRepo.findById(id).orElse(null);
        if(r!=null){
            r.setText(reply.getText());
            r.setLikes(reply.getLikes());
            return replyRepo.save(r);
        }
        return null;
    }

    public Reply deleteReply(long id){
        Reply r = replyRepo.findById(id).orElse(null);
        if(r!=null){
            replyRepo.deleteById(id);
            return r;
        }
        return null;
    }

    public Reply addLikesToQuestion(long id, int like) {
        Reply reply = replyRepo.findById(id).orElse(null);

        if (reply != null) {
            reply.setLikes(reply.getLikes() + 1);
            replyRepo.save(reply);
            return reply;
        }
        return null;
    }
}
