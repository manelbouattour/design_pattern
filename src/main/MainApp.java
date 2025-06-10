package main;
import javafx.scene.control.Button;

import dp_command_draw.*;
import dp_enumerations.ShapeType;
import dp_factory_shape.*;
import dp_observer_selection_shape.*;
import dp_repository.DrawingModel;
import dp_singleton_log.LoggerManager;
import dp_strategy_draw.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class MainApp extends Application {
    private DrawingModel model = new DrawingModel();
    private ShapeSelectionSubject selectionSubject = new ShapeSelectionSubjectImpl();
    private DrawStrategy currentStrategy;
    private double startX, startY;

    private Canvas canvas;
    private GraphicsContext gc;
   
    // Factories avec gestion de couleur
    private CircleFactory circleFactory = new CircleFactory();
    private RectangleFactory rectangleFactory = new RectangleFactory();
    private LineFactory lineFactory = new LineFactory();
    private Color currentColor = Color.BLACK;

    @Override
    public void start(Stage stage) {

        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
       

        updateFactoriesColor(currentColor);
       
     
        currentStrategy = new FactoryBasedStrategy(rectangleFactory);

        ToolPalette palette = new ToolPalette(selectionSubject);
       
        ColorPicker colorPicker = new ColorPicker(currentColor);
        colorPicker.setOnAction(e -> {
            currentColor = colorPicker.getValue();
            updateFactoriesColor(currentColor);
        });
       

        Button clearButton = new Button("Effacer");
        clearButton.setOnAction(e -> clearDrawing());

        HBox buttonBox = new HBox(10, clearButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_LEFT);
       
        VBox topBox = new VBox(10, palette, colorPicker, buttonBox);
        topBox.setPadding(new Insets(10));
       
        ShapeObserver shapeObserver = selected -> {
            switch (selected) {
                case CIRCLE:
                    currentStrategy = new FactoryBasedStrategy(circleFactory);
                    break;
                case RECTANGLE:
                    currentStrategy = new FactoryBasedStrategy(rectangleFactory);
                    break;
                case LINE:
                    currentStrategy = new FactoryBasedStrategy(lineFactory);
                    break;
            }
        };
        selectionSubject.addObserver(shapeObserver);

        canvas.setOnMousePressed(e -> {
            startX = e.getX();
            startY = e.getY();
        });

        canvas.setOnMouseReleased(e -> {
            try {
                Shape shape = currentStrategy.draw(startX, startY, e.getX(), e.getY());
                new AddShapeCommand(model, shape).execute();
                redrawAllShapes();
            } catch (Exception ex) {
                showError("Erreur de dessin", ex.getMessage());
            }
        });

        BorderPane root = new BorderPane();
        root.setTop(topBox); 
        root.setCenter(canvas);

        stage.setScene(new Scene(root));
        stage.setTitle("Éditeur Graphique avec Design Patterns");
        stage.show();

        clearCanvas();
    }
    private void saveDrawing() {
        LoggerManager.INSTANCE.log("Dessin enregistré à " + java.time.LocalDateTime.now());
    }

    private void clearDrawing() {
        model.getShapes().clear();
        clearCanvas();
        LoggerManager.INSTANCE.log("Dessin effacé");

    }
    private void updateFactoriesColor(Color color) {
        circleFactory.setStrokeColor(color);
        rectangleFactory.setStrokeColor(color);
        lineFactory.setStrokeColor(color);
    }

    private void redrawAllShapes() {
        clearCanvas();
        for (Shape shape : model.getShapes()) {
            drawShape(gc, shape);
        }
    }

    private void drawShape(GraphicsContext gc, Shape shape) {
        if (shape instanceof javafx.scene.shape.Rectangle r) {
            gc.setStroke(r.getStroke());
            gc.setLineWidth(r.getStrokeWidth());
            gc.strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        } else if (shape instanceof javafx.scene.shape.Circle c) {
            gc.setStroke(c.getStroke());
            gc.setLineWidth(c.getStrokeWidth());
            gc.strokeOval(c.getCenterX() - c.getRadius(),
                    c.getCenterY() - c.getRadius(),
                    c.getRadius() * 2,
                    c.getRadius() * 2);
        } else if (shape instanceof javafx.scene.shape.Line l) {
            gc.setStroke(l.getStroke());
            gc.setLineWidth(l.getStrokeWidth());
            gc.strokeLine(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
        }
    }

    private void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
   
}