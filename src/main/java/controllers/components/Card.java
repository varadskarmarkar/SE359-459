package controllers.components;

import javafx.geometry.Point2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Card extends StackPane {
    final int id;
    Enum name;
    String ques;
    Point2D position;
    Double width;
    Double hight;
    Rectangle rec;

    public Card() {
        this.id = 0;
        this.name = null;
        this.ques = "whattt";
    }

    public Card(final int id, Point2D pos, double w, double h, Enum name, String q) {
        this.id = id;
        this.name = name;
        this.ques = q;
        this.width = w;
        this.hight = h;

        position = pos;
        setLayoutX(pos.getX());
        setLayoutY(pos.getY());

        rec = new Rectangle(pos.getX(), pos.getY(), w, h);
        rec.setStroke(Color.BLACK);
        rec.setFill(Color.GRAY);

        Text text = new Text(id + "\n" + name + "\n" + q);
        getChildren().addAll(rec, text);

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
}
