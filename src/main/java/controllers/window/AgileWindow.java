package controllers.window;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

public class AgileWindow extends Popup {

    private Rectangle messageArea;
    private StackPane messageContainer;

    public AgileWindow () {
        this.messageContainer = new StackPane();
        this.messageArea = new Rectangle();
        this.messageArea.setArcHeight(20);
        this.messageArea.setArcWidth(20);
        /*
            Hard coded style for the rectangle.
            From here: https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#introscenegraph
         */
        this.messageArea.setStyle("-fx-fill: honeydew; -fx-stroke: red; -fx-stroke-dash-array: 12 2 4 2;" +
                " -fx-stroke-width: 5; -fx-stroke-dash-offset: 6;  -fx-stroke-line-cap: butt");
        super.getContent().addAll(messageContainer, messageArea);
    }


    /**
     * Set the height of the Pop-up window.
     * @param height
     */
    public void setWindowHeight (double height) {
        this.messageArea.setHeight(height);
    }

    /**
     * Set the width of the Pop-up window.
     * @param windowWidth
     */
    public void setWindowWidth (double windowWidth) {
        this.messageArea.setWidth(windowWidth);
    }

    /**
     * Set the color of the message box.
     * @param color
     */
    public void setColor (Color color) {
        this.messageArea.setFill(color);
    }


    /**
     * Set the text for the message box.
     * @param label
     */
    public void setText (Label label) {
        this.messageContainer.getChildren().addAll(messageArea, label);
    }
}
