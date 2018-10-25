package controllers.components;

import java.util.*;

public class QuestionPool {
    List<Question> questions;

    public QuestionPool(){
        this.questions = new ArrayList<Question>();
    }
    public void addQuestion(Question q){
        this.questions.add(q);
    }
    public Question getQuestion(int id){
        for(Question q : this.questions){
            if(q.getqID()==id){
                return q;
            }
        }
        return null;
    }
    public int getSize(){
        return this.questions.size();
    }
}
