package controllers.components;

import javafx.geometry.Point2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.*;
import java.util.*;


public class Card extends StackPane {

    private final int id;
    private Enum name;
    private String ques;
    private Point2D position;
    private Double width;
    private Double hight;
    private Rectangle rec;
    private HashMap <Integer, String> questionPool;


    public Card() {
        this.id = 0;
        this.name = N.X;
        this.ques = "whattt";

        this.position = new Point2D(80,70);
        this.setLayoutX(this.position.getX());
        this.setLayoutY(this.position.getY());

        this.rec = new Rectangle(this.position.getX(), this.position.getY());
        this.rec.setStroke(Color.BLACK);
        this.rec.setFill(Color.GRAY);


        Text text = new Text(this.id + "\n" + this.name + "\n" + ques);
        getChildren().addAll(rec, text);
    }

        public Card(final int id, Point2D pos, double w, double h, Enum name, String q) {
        this.id = id;
        this.name = name;
        this.ques = q;
        this.width = w;
        this.hight = h;

        this.position = pos;
        this.setLayoutX(pos.getX());
        this.setLayoutY(pos.getY());

        this.rec = new Rectangle(this.position.getX(), this.position.getY(), this.width, this.hight);
        this.rec.setStroke(Color.BLACK);
        this.rec.setFill(Color.GRAY);

        Text text = new Text(id + "\n" + this.name + "\n" + this.ques);
        getChildren().addAll(this.rec, text);

    }


    public void move(double x, double y) {
        y = getLayoutY() + y;
        x = getLayoutX() + x;

        setLayoutX(x);
        setLayoutY(y);
        rec.setX(x);
        rec.setY(y);
    }



    // setter and getters will be added later
    public Enum getName() {
        return this.name;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public boolean contains(Point2D point) {
        return (rec.contains(point));
    }

    public void setOrignalPos() {
        setLayoutX(position.getX());
        setLayoutY(position.getY());
        rec.setX(position.getX());
        rec.setY(position.getY());
    }
    public void placeChip() {
        rec.setFill(Color.RED);
    }



    public String getRandomQuestion() {
        Random randomizes = new Random();
        int bound = randomizes.nextInt(questionPool.keySet().size());
        String question = questionPool.get(bound);
        // Avoids an empty line in the file.
        return question == null ? question = questionPool.get(1) : question;
    }

    /**
     * Retrieves the questions from the file full of questions.
     * @return
     */
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
}
