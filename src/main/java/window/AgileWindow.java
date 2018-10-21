package window;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

public class AgileWindow extends Popup {

    private Rectangle messageArea;
    private StackPane messageContainer;
    private Label label;

    public AgileWindow () {
        initWindow();
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
     * Set the text for the message box.
     * @param label
     */
    public void setText (Label label) {
        if (label == null) {
            this.label = new Label("You have not set any text\nInsert your text here.");
        }
        this.label = label;
        this.messageContainer.getChildren().addAll(this.messageArea, this.label);
    }

    /*
        Initializes the window with default settings.
     */
    private void initWindow () {
        Button closeButton;
        closeButton = new Button("✖");
        closeButton.setStyle("    -fx-background-color: linear-gradient(#ff5400, #be1d00);\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0;\n" +
                "    -fx-text-fill: white;");

        closeButton.setOnAction((closingButtonEvent ->  {
            super.hide();
        }));

        this.messageContainer = new StackPane();
        this.messageArea = new Rectangle();
        this.messageArea.setArcHeight(30);
        this.messageArea.setArcWidth(30);
        this.messageArea.setStyle(
                "-fx-fill: white; -fx-stroke: lightslategrey; " +
                        "-fx-stroke-dash-array: 12 2 4 2;" +
                        " -fx-stroke-width: 5; " +
                        "-fx-stroke-dash-offset: 6; " +
                        " -fx-stroke-line-cap: round");
        super.getContent().addAll(messageContainer, messageArea, closeButton);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AgileWindow: { \n");
        sb.append("\tmessageArea=").append(messageArea).append(",\n");
        sb.append("\tmessageContainer=").append(messageContainer).append(",\n");
        sb.append("\tlabel=").append(label).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
