package controllers.window;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;

public class CongratulationWindow extends AgileWindow {

    public CongratulationWindow () {
        super();
    }

    @Override
    public void setWindowHeight(double height) {
        super.setWindowHeight(height);
    }


    @Override
    public void setWindowWidth(double windowWidth) {
        super.setWindowWidth(windowWidth);
    }

    /**
     *
     * @return the congratulation image.
     */
    public ImageView setUpImage() {
        ImageView imageView = null;
        try {
            // Load image
            ClassLoader classLoader = getClass().getClassLoader();
            String imageLocation = classLoader.getResource("images/goodjob.jpg").toExternalForm();
            // Configure image
            Image congratulation_image = new Image(imageLocation);
            imageView = new ImageView(congratulation_image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(220);
        }
        catch (Exception exception) {
            System.out.println("Exception:" + exception.getMessage() +
                    " " + exception.getCause());
        }
        return imageView;
    }


    public void showCongratWindow(Window owner) {
        super.showWindow(owner);
    }


    public void hideCongratWindow() {
        super.hideWindow();
    }

    @Override
    public void setText(Label label) {
        super.setText(label);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
