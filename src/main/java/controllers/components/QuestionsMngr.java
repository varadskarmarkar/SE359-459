package controllers.components;

import javafx.beans.binding.StringBinding;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionsMngr {
    Map<Integer, Boolean> usedQuestion;
    int numOfQuestion;
    QuestionPool questionPool;
    List<Integer> seenQustion;
    int currentQ;
    String fileName;


//    public QuestionsMngr(){
//        this.questionPool = new QuestionPool();
//        // if you want to add question manually
//        //e.g. questionPool.addQuestion(new Question(0, "question", "answer", new ArrayList<String>()));
//        this.numOfQuestion = this.questionPool.getSize();
//        this.usedQuestion = new HashMap<Integer, Boolean>();
//        for (int i = 0; i < numOfQuestion; i++){
//            this.usedQuestion.put(i, false);
//
//        }
//        this.seenQustion = new ArrayList<Integer>();
//
//    }

    public QuestionsMngr(String mode){
        if (mode.equals("Monty Python")) {
            fileName = "montyPythonQuestions.txt";
        } else {
            fileName = "questions1.txt";
        }

        currentQ = 1;
        this.questionPool = new QuestionPool();

        StringBuilder stringBuilder = new StringBuilder();
        String path = "files/"+fileName;
        ArrayList<Question> questionsFromFile = new ArrayList<Question>();
        try {

            ClassLoader loader = getClass().getClassLoader();
            File questionsFile = new File(loader.getResource(path).getFile());
            Scanner scanner = new Scanner(questionsFile);


            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                stringBuilder.append(line);
            }
            scanner.close();

        } catch (IOException exception) {
            System.out.println("Exception:"
                    + exception.getMessage()
                    + " " + exception.getCause());
        }


        String[] questions = stringBuilder.toString().split("q: ");

//        StringTokenizer st = new StringTokenizer(stringBuilder.toString(), "q: ",true);
        int i = 1;
        while (questions.length > i){
                // get id
                Question q = new Question();
                q.setqID(i);
                String[] ans = questions[i].split("a: ");
                // get question
//                System.out.println("Qustion: "+ ans[0]);
                q.setQuestion(ans[0]);
                // get answer

                String[] opt = ans[1].split("opt: ");
//                System.out.println("Answer : "+opt[0]);
                q.setAnswer(opt[0]);
                for( int j = 1; opt.length > j; j++){
//                    System.out.println("option "+j+": "+opt[j]);
                    q.addOption(opt[j]);
                }


                i++;
                questionPool.addQuestion(q);
        }
        this.numOfQuestion = this.questionPool.getSize();
        this.usedQuestion = new HashMap<Integer, Boolean>();
        for (int x = 0; x < numOfQuestion; x++){
            this.usedQuestion.put(x, false);

        }
        this.seenQustion = new ArrayList<Integer>();
//        System.out.println(numOfQuestion);

    }
    public Question getNextQuestion(){
        // if
        if(this.currentQ>this.numOfQuestion){
            currentQ = 1;
        }


        Question q = this.questionPool.getQuestion(currentQ);
        currentQ++;
        return q;
    }


}
