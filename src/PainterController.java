// Fig. 13.6: PainterController.java
// Controller for the Painter app
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class PainterController {
    // enum representing pen sizes
    private enum PenSize {
        SMALL(2),
        MEDIUM(4),
        LAGRE(6);

        private final int radius;

        PenSize(int radius) {this.radius = radius;} // constructor

        public int getRadius() {return radius;}
    };

    // instance variables that refer to GUI components
    @FXML private RadioButton blackRadioButton;
    @FXML private RadioButton redRadioButton;
    @FXML private RadioButton greenRadioButton;
    @FXML private RadioButton blueRadioButton;
    @FXML private RadioButton smallRadioButton;
    @FXML private RadioButton mediumRadioButton;
    @FXML private RadioButton largeRadioButton;
    @FXML private Pane drawingAreaPane;
    @FXML private ToggleGroup colorToggleGroup;
    @FXML private ToggleGroup sizeToggleGroup;

    // instance variables for managing Painter state
    private PenSize radius = PenSize.MEDIUM; // radius of circle
    private Paint brushColor = Color.BLACK; // drawing color

    // set user data for the RadioButtons
    public void initialize() {
        // user data on a control can be any object
        blackRadioButton.setUserData(Color.BLACK);
        redRadioButton.setUserData(Color.RED);
        greenRadioButton.setUserData(Color.GREEN);
        blueRadioButton.setUserData(Color.BLUE);
        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LAGRE);
    }

    // handles drawingArea's onMouseDragged MouseEvent
    @FXML
    private void drawingAreaMouseDragged(MouseEvent event) {
        Circle newCircle = new Circle(event.getX(), event.getY(),
                radius.getRadius(), brushColor);
        drawingAreaPane.getChildren().add(newCircle);
    }

    // handles color RadiusButton's ActionEvents
    @FXML
    private void colorRadioButtonSelected(ActionEvent event) {
    // user data for each color RadioButton is the corresponding Color
        brushColor =
                (Color) colorToggleGroup.getSelectedToggle().getUserData();
    }

    // handles size RadioButton's ActionEvents
    @FXML
    private void sizeRadioButtonSelected(ActionEvent event) {
        // user data for each size
        radius =
            (PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
    }

    // handles Undo
    @FXML
    private void undoButtonPressed(ActionEvent event) {
        int count = drawingAreaPane.getChildren().size();

        // if there are any shapes remove the last one added
        if (count > 0) {
            drawingAreaPane.getChildren().remove(count - 1);
        }
    }

    // handles Clear
    @FXML
    private void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }
}