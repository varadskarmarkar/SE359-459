package controllers.components;
import java.io.*;
import java.util.*;

/**
 * Julio
 */
public class QuestionGenerator {

    private HashMap <Integer, String> questionPool;

    public QuestionGenerator() {
        this.fillQuestionPool();
    }


    private List<String> retrieveQuestionsFromFile() {
        List <String> questionsFromFile = new ArrayList<>();
        try {

            ClassLoader loader = getClass().getClassLoader();
            File questionsFile = new File(loader.getResource("files/questions.txt").getFile());
            Scanner scanner = new Scanner(questionsFile);

            while (scanner.hasNextLine()){
                String question = scanner.nextLine();
                questionsFromFile.add(question);
            }
            scanner.close();

        } catch (IOException exception) {
            System.out.println("Exception:"
                    + exception.getMessage()
                    + " " + exception.getCause());
        }
        return questionsFromFile;
    }
    /**
     * Fills the HashMap
     */
    private void fillQuestionPool() {
        questionPool = new HashMap<>();
        List<String> questions = retrieveQuestionsFromFile();

        int max_in_keySet = questions.size();
        for (String question: questions) {
            questionPool.put(max_in_keySet--, question);
        }
    }

    /**
     *
     * @return a random question
     */
    public String getRandomQuestion() {
        Random randomizes = new Random();
        int bound = randomizes.nextInt(questionPool.keySet().size());
        String question = questionPool.get(bound);
        // Avoids an empty line in the file.
        return question == null ? question = questionPool.get(1) : question;
    }
}
