package dp_observer_selection_shape;

import dp_enumerations.ShapeType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class ToolPalette extends HBox {
    private final ShapeSelectionSubject selectionSubject;

    public ToolPalette(ShapeSelectionSubject selectionSubject) {
        this.selectionSubject = selectionSubject;
        initializeUI();
        applyLightStyle();
    }

    private void initializeUI() {
        ToggleGroup group = new ToggleGroup();

        // Rectangle button
        ToggleButton btnRectangle = createToolButton("Rectangle", ShapeType.RECTANGLE, group,
                new Rectangle(18, 12) {{
                    setStroke(Color.BLACK);
                    setFill(Color.TRANSPARENT);
                    setStrokeWidth(2);
                }});

        // Circle button
        ToggleButton btnCircle = createToolButton("Cercle", ShapeType.CIRCLE, group,
                new Circle(9) {{
                    setStroke(Color.BLACK);
                    setFill(Color.TRANSPARENT);
                    setStrokeWidth(2);
                }});

        // Line button
        ToggleButton btnLine = createToolButton("Ligne", ShapeType.LINE, group,
                new Line(0, 0, 18, 0) {{
                    setStroke(Color.BLACK);
                    setStrokeWidth(2);
                }});

        // Add buttons to the toolbar
        this.getChildren().addAll(btnRectangle, btnCircle, btnLine);

        // Default selection
        btnRectangle.setSelected(true);
        selectionSubject.notifyObservers(ShapeType.RECTANGLE);

        // Update observers on selection change
        group.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null) {
                selectionSubject.notifyObservers(null);
            } else {
                ShapeType selectedType = (ShapeType) newVal.getUserData();
                selectionSubject.notifyObservers(selectedType);
            }
        });
    }

    private ToggleButton createToolButton(String text, ShapeType type, ToggleGroup group, Shape icon) {
        ToggleButton button = new ToggleButton(text, icon);
        button.setToggleGroup(group);
        button.setUserData(type);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setContentDisplay(ContentDisplay.LEFT);
        button.setPadding(new Insets(8, 15, 8, 10));
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #e0e0e0; -fx-text-fill: black;"));
        button.setOnMouseExited(e -> {
            if (button.isSelected()) {
                button.setStyle("-fx-background-color: #c5cae9; -fx-text-fill: black;");
            } else {
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
            }
        });

        button.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                button.setStyle("-fx-background-color: #c5cae9; -fx-text-fill: black;");
            } else {
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
            }
        });

        return button;
    }

    private void applyLightStyle() {
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 5;");
    }
}
