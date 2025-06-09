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
        applyModernStyle();
    }
    
    private void initializeUI() {
        ToggleGroup group = new ToggleGroup();
        
        ToggleButton btnRectangle = createToolButton("Rectangle", ShapeType.RECTANGLE, group, 
            new Rectangle(18, 12, Color.TRANSPARENT) {{
                setStroke(Color.WHITE);
                setStrokeWidth(2);
            }});
        
        ToggleButton btnCircle = createToolButton("Cercle", ShapeType.CIRCLE, group,
            new Circle(9, Color.TRANSPARENT) {{
                setStroke(Color.WHITE);
                setStrokeWidth(2);
            }});
        
        ToggleButton btnLine = createToolButton("Ligne", ShapeType.LINE, group,
            new Line(0, 0, 18, 0) {{
                setStroke(Color.WHITE);
                setStrokeWidth(2);
            }});
        
        this.getChildren().addAll(btnRectangle, btnCircle, btnLine);
    }
    
    private ToggleButton createToolButton(String text, ShapeType type, ToggleGroup group, Shape icon) {
        ToggleButton button = new ToggleButton(text, icon);
        button.setToggleGroup(group);
        button.setUserData(type);
        button.setAlignment(Pos.CENTER_LEFT);
        button.setContentDisplay(ContentDisplay.LEFT);
        button.setPadding(new Insets(8, 15, 8, 10));
        
        button.setOnAction(e -> {
            selectionSubject.notifyObservers(type);
            applyButtonSelectionStyle(button, true);
        });
        
        // Style pour le premier bouton sélectionné par défaut
        if (type == ShapeType.RECTANGLE) {
            button.setSelected(true);
            applyButtonSelectionStyle(button, true);
        }
        
        return button;
    }
    
    private void applyModernStyle() {
        this.setSpacing(5);
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: #3f51b5; -fx-background-radius: 5;");
    }
    
    private void applyButtonSelectionStyle(ToggleButton button, boolean selected) {
        if (selected) {
            button.setStyle("-fx-background-color: #5c6bc0; -fx-text-fill: white; -fx-font-weight: bold;");
        } else {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: #e8eaf6;");
        }
    }
}