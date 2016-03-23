package sceneElements;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * @author : First created by Paul Popa with code by Paul Popa
 * @date : 09/02/16, last edited by Paul Popa on 09/02/16
 * <p>
 * This class makes the setting of the label properties more easily
 */
public class LabelProperties {

    /**
     * Sets the properties for the label
     *
     * @param label     the label we will use
     * @param posX      the x position of the label
     * @param posY      the y position of the label
     * @param imageView the image placed on the label
     */
    public void setLabelProperties(Label label, double posX, double posY, ImageView imageView) {

        label.setLayoutX(posX);
        label.setLayoutY(posY);
        label.setGraphic(imageView);
    }
}
