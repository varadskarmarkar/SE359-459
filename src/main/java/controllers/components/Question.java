package controllers.components;

import java.util.*;

public class Question {
    int qID;
    String question;
    String answer;
    List<String> options;

    public Question(){
        this.qID = 0;
        this.question = "";
        this.answer = "";
        this.options = new ArrayList<String>();
    }
    public Question(int id, String ques, String ans, List<String> opts){
        this.qID = id;
        this.question = ques;
        this.answer = ans;
        this.options = (ArrayList<String>)opts;
    }

    public void setqID(int id){
        this.qID = id;
    }
    public int getqID(){
        return this.qID;
    }
    public void setQuestion(String q){
        this.question = q;
    }
    public String getQuestion(){
        return this.question;
    }
    public void setAnswer(String a){
        this.answer = a;
    }
    public String getAnswer(){
        return this.answer;
    }
    public void addOption(String opt){
        this.options.add(opt);
    }
    public void addOptions(List<String> opts){
        this.options.addAll((ArrayList<String>)opts);
    }
    public List<String> getOptions(){
        return this.options;
    }

}
